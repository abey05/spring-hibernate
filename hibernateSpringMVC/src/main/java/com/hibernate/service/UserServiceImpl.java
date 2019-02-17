package com.hibernate.service;

import com.hibernate.dao.UserDao;
import com.hibernate.model.*;
import com.hibernate.model.PassbookEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<UserEvent> findAllUsers(){
        return userDao.findAllUsers();
    }
    @Override
    @Transactional
    public void addUser(UserEvent user){
        userDao.addUser(user);
    }
    @Override
    @Transactional
    public void addUsers(Users users){userDao.addUsers(users);}
    @Override
    @Transactional
    public void addUserRoles(UserRoles userRoles){userDao.addUserRoles(userRoles);}
    @Override
    @Transactional
    public void Update(UserEvent user){
        userDao.Update(user);
    }
    @Override
    @Transactional
    public void UpdateUsers(Users users){userDao.UpdateUsers(users);}
    @Override
    @Transactional
    public void UpdateUserRoles(UserRoles roles){userDao.UpdateUserRole(roles);}
    @Override
    @Transactional
    public void deleteUser(long id){
        userDao.deleteUser(id);
    }
    @Override
    @Transactional
    public void deleteUsers(String username){userDao.deleteUsers(username);}
    @Override
    @Transactional
    public void addWalletBalance(long id, double balance){
        userDao.addWalletBalance(id,balance);
    }
    @Override
    @Transactional
    public boolean debitWalletBalance(long id, double balance){return userDao.debitWalletBalance(id,balance);}
    @Override
    @Transactional
    public UserEvent getUserById(long id){
       return userDao.getUserById(id);
    }
    @Override
    @Transactional
    public void addTransactionDetail(PassbookEvent passbookEvent){userDao.addTransactionDetail(passbookEvent);}
    @Override
    @Transactional
    public List<PassbookEvent> showPassbook(long userId, String type){return userDao.showPassbook(userId,type);}
    @Override
    @Transactional
    public UserEvent getUserByUsername(String username){return userDao.getUserByUsername(username);}
    @Override
    @Transactional
    public UserEvent getUserByMobileNo(String mobileNo){return userDao.getUserByMobileNo(mobileNo);}
    @Override
    @Transactional
    public UserRoles getUserRolesByUsername(String username){return userDao.getUserRolesByUsername(username);}

}