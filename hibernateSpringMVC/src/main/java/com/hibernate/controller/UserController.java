package com.hibernate.controller;

import com.hibernate.model.PassbookEvent;
import com.hibernate.model.UserEvent;
import com.hibernate.model.UserRoles;
import com.hibernate.model.Users;
import com.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PassbookEvent passbookEvent;
    private boolean isAdmin(Authentication authentication){
        Collection<? extends GrantedAuthority > authorities = authentication.getAuthorities();
        for (GrantedAuthority authority: authorities){
            if(authority.getAuthority().compareTo("ROLE_ADMIN")==0)
                return true;
        }
        return false;
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }
    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model , Authentication authentication) {
        System.out.println("Username="+ authentication.getName() );
        if(isAdmin(authentication)) {
            model.addAttribute("user", new UserEvent());
            model.addAttribute("userList", userService.findAllUsers());
            return "index"; //homepage
        }
        model.addAttribute("user",userService.getUserByUsername(authentication.getName()));//userEvent
        return "indexUser";
    }
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newUser(ModelMap model ){
        model.addAttribute("command", new UserEvent()); //command
        return "form";
    }
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute UserEvent user , @ModelAttribute Users users, @ModelAttribute UserRoles roles){
        userService.addUser(user);
        users.setEnabled(1);
        userService.addUsers(users);
        roles.setRole("ROLE_USER");
        userService.addUserRoles(roles);
        return "redirect:/";
    }
    @RequestMapping(value="edit/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute UserEvent user,@ModelAttribute Users users, @ModelAttribute UserRoles roles,Authentication authentication){
        //Should be Before update
        //if username is changed
        UserEvent previousUser = userService.getUserById(user.getId());
        if(previousUser.getUsername().compareTo(user.getUsername()) != 0 ){
            UserRoles previousUserRoles = userService.getUserRolesByUsername(previousUser.getUsername());
            roles.setUserRoleId(previousUserRoles.getUserRoleId());
            roles.setRole("ROLE_USER");//default
            users.setEnabled(1);
            userService.addUsers(users);
            userService.UpdateUserRoles(roles);
            userService.deleteUsers(previousUser.getUsername());
        }
        if (previousUser.getPassword().compareTo(user.getPassword()) != 0){
            users.setEnabled(1);
            userService.UpdateUsers(users);
        }

        userService.Update(user);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        return "redirect:/";
    }
    @RequestMapping(value = "/edit/id={id}", method=RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") long id ){ 

        ModelAndView model = new ModelAndView("editForm");
        model.addObject("command", userService.getUserById(id));
        return model;
    }
    @RequestMapping(value = "/delete/id={id}")
    public String deleteUser(@PathVariable("id") long id ){ 
        userService.deleteUser(id);
        return "redirect:/";
    }
    @RequestMapping(value="/walletAmount", method= RequestMethod.GET)
    public String getAmount(long id){
        ModelMap model = new ModelMap();
        model.addAttribute("id",id );
        return "amountForm";
    }
    @RequestMapping(value ="/addMoney/id={id}", method = RequestMethod.GET)
    public String addWalletMoney (@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("id", id);
        return "redirect:http://localhost:8080/generateOtp/";
    }
    @RequestMapping(value="/saveMoneyWallet/{id}/{amount:.+}/{accountNo}")
    public ResponseEntity<String> /*String*/ saveInWallet(@PathVariable("id")String userId, @PathVariable("amount")String amount, @PathVariable("accountNo") String accountNo){
        userService.addWalletBalance(Integer.parseInt(userId),Double.parseDouble(amount));
        passbookEvent.setUserId(Integer.parseInt(userId));
        passbookEvent.setDebitAccount(Integer.parseInt(accountNo));
        passbookEvent.setType("credit");
        passbookEvent.setAmount(Double.parseDouble(amount));
        passbookEvent.setTimestamp(new Timestamp(System.currentTimeMillis()));
        userService.addTransactionDetail(passbookEvent);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @RequestMapping(value="/pay/{id}")
    public ModelAndView payMoney(@PathVariable("id") long id){
        ModelAndView model = new ModelAndView("payForm");
        model.addObject("command", new PassbookEvent());
        return model;
    }
    //ajax
    @RequestMapping(value="getWalletBalance")
    @ResponseBody
    public String getWalletBalance(@RequestParam("id") String id){
        return userService.getUserById(Long.parseLong(id)).getWalletBalance() +"";
    }
    //Debit
    @RequestMapping(value="pay/commit")
    public String payCommit(@ModelAttribute PassbookEvent passbookEvent ) {

        if(userService.debitWalletBalance(passbookEvent.getUserId(),passbookEvent.getAmount())){
            userService.addTransactionDetail(passbookEvent);//debit
            UserEvent userToPay = userService.getUserByMobileNo(passbookEvent.getCreditWallet());
            userService.addWalletBalance(userToPay.getId(),passbookEvent.getAmount());
            System.out.println("amount transferred successfully");
        }
        else
            System.out.println("unable to transfer amount");
        return "redirect:/";
    }
    /*  passbook   */
    @RequestMapping(value = "showPassbook/id={id}", method = RequestMethod.GET)
    public String showPassbook(@PathVariable("id")long userId ,ModelMap model ){
        model.addAttribute("txnCredit", new PassbookEvent());
        model.addAttribute("txnListCredit",userService.showPassbook(userId, "credit"));
        model.addAttribute("txnDebit", new PassbookEvent());
        model.addAttribute("txnListDebit",userService.showPassbook(userId,"debit"));
        return "passbookShow";
    }

}











 /*@RequestMapping(value="addMoney/saveMoneyWallet/id={id}",method = RequestMethod.POST)
    public String saveMoneyInWallet(@RequestParam("amount") double amount ,@RequestParam("accountNo") long accountNo , @PathVariable("id") long id ){

        System.out.println("ID = "  + id + " Amount =  " + amount);
        String url = "http://localhost:8080/transaction/{id}/{amount}/{accountNo}";
        //String url = "http://localhost:8080/initiate/{id}/{amount}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String > map = new HashMap<String,String>();
        map.put("id",id +"");
        map.put("amount",amount+"");
        map.put("accountNo", accountNo+"");
        //System.out.println("in map"+  map.get("amount"));
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class,map);
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(map,headers);
        *//*ResponseEntity<String > response = restTemplate.postForEntity("http://localhost:8081", request,String.class);*//*
 *//*ResponseEntity<String > response = restTemplate.exchange("http://localhost:8080/transaction/{id}/{amount}", HttpMethod.GET,request,String.class);*//*

        if(response.getBody().compareTo("Success")== 0){
            userService.addWalletBalance(id,amount);
            passbookEvent passbookEvent = new passbookEvent();
            passbookEvent.setUserId(id);
            passbookEvent.setDebitAccount(accountNo);
            passbookEvent.setAmount(amount);
            passbookEvent.setTimestamp(new Timestamp(System.currentTimeMillis()));
            userService.addTransactionDetail(passbookEvent);
        }
        else{
            System.out.println("Response "+response.getBody());
            return "redirect:/addMoney/id=" + id;
        }
        return "redirect:/";
    }*/
