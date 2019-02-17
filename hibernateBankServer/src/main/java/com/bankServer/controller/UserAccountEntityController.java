package com.bankServer.controller;

import com.bankServer.model.OrderOtpEntity;
import com.bankServer.model.UserAccountEntity;
import com.bankServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class UserAccountEntityController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("user", new UserAccountEntity());
        model.addAttribute("userList",userService.listUsers());
        return "index";
    }
    @RequestMapping (value="/new",method = RequestMethod.GET)
    public String newUserAccount(ModelMap model){
        model.addAttribute("command", new UserAccountEntity());
        return "addAccount";
    }
    @RequestMapping(value="/saveAccount", method = RequestMethod.POST)
    public String addAccount(@ModelAttribute UserAccountEntity userAccountEntity){
        if(userAccountEntity.getAccountNo() == 0)
                userService.add(userAccountEntity);
        else
                userService.update(userAccountEntity);
        return "redirect:/";
    }
    @RequestMapping(value="edit/updateAccount", method = RequestMethod.POST)
    public String updateAccount(@ModelAttribute UserAccountEntity userAccountEntity ){
        userService.update(userAccountEntity);
        return "redirect:/";
    }
    //update edit
    @RequestMapping(value="edit/id={accountNo}")
    public String editAccount(@PathVariable("accountNo")long accountNo, ModelMap model){
        model.addAttribute("command", userService.getUserByAccountNo(accountNo));
        return "editAccount";
    }
    @RequestMapping(value="deleteAccount/id={accountNo}")
    public String deleteAccount(@PathVariable("accountNo") long accountNo){
        userService.deleteAccount(accountNo);
        return "redirect:/";
    }
   private boolean deductMoney(long accountNo, double amount){
        if(!userService.isAccount(accountNo))
            return false;
        return userService.deductMoney(accountNo,amount);
   }
   @RequestMapping(value="/generateOtp") // hit from 8085
   public String generateOtp (@RequestParam("id") long userId, ModelMap model){
        System.out.println("ajax"+userId);
      // System.out.println(request.getHeader("Referer"));
       model.addAttribute("userId", userId);
       model.addAttribute("command", new OrderOtpEntity());
        //return ResponseEntity.status(HttpStatus.OK).body("Success" );
       long otp = ThreadLocalRandom.current().nextInt(234563, Integer.MAX_VALUE%(1<<19));
       OrderOtpEntity order = new OrderOtpEntity();
       order.setOtp(otp);
       order.setUserId(userId);
        long orderId = userService.addOtp(order);
       model.addAttribute("orderId", orderId);
        return "amountForm";
   }
   @RequestMapping(value="/verifyOtp")
   @ResponseBody
   public String verifyOtp(HttpServletRequest request,@RequestParam String orderId){
        System.out.println(request);
        System.out.println(orderId);
       return userService.getOrderByOrderId(Integer.parseInt(orderId)).getOtp()+"";
   }

   @RequestMapping(value = "generateOtp/commitTransaction")
   public String commitTransaction(@ModelAttribute OrderOtpEntity order){
        System.out.println(order);
        userService.updateOtpEntity(order);
        if(this.deductMoney(order.getAccountNo(), order.getAmount())) {
            System.out.println("Txn Successful");
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String > map = new HashMap<String,String>();
            map.put("id",order.getUserId() +"");
            map.put("amount",order.getAmount()+"");
            map.put("accountNo", order.getAccountNo()+"");
            String url = "http://localhost:8085/saveMoneyWallet/{id}/{amount}/{accountNo}";
           ResponseEntity<String> response = restTemplate.getForEntity(url,String.class,map);
            if(response.getBody().compareTo("Success")==0)
                return "redirect:http://localhost:8085/";
        }
        else
            System.out.println("Txn failed");
        return "redirect:http://localhost:8085/";
   }
}
