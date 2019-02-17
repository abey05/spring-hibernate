package com.bankServer.service;

import com.bankServer.dao.UserDao;
import com.bankServer.model.OrderOtpEntity;
import com.bankServer.model.UserAccountEntity;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public void add(UserAccountEntity userAccountEntity){
        userDao.add(userAccountEntity);
    }
    @Override
    @Transactional
    public void update(UserAccountEntity userAccountEntity){
        userDao.update(userAccountEntity);
    }
    @Override
    @Transactional
    public List<UserAccountEntity> listUsers(){
        return userDao.listUsers();
    }
    @Override
    @Transactional
    public UserAccountEntity getUserByAccountNo(long accountNo){
        return userDao.getUserByAccountNo(accountNo);
    }
    @Override
    @Transactional
    public boolean deductMoney(long accountNo , double balance){
        return userDao.deductMoney(accountNo,balance);
    }
    @Override
    @Transactional
    public void deleteAccount(long accountNo){
        userDao.deleteAccount(accountNo);
    }
    @Override
    @Transactional
    public boolean isAccount(long accountNo){
       return userDao.isAccount(accountNo);
    }
    @Override
    @Transactional
    public Long addOtp(OrderOtpEntity orderOtpEntity){return userDao.addOtp(orderOtpEntity);}
    @Override
    @Transactional
    public void updateOtpEntity(OrderOtpEntity orderOtpEntity){userDao.updateOtpEntity(orderOtpEntity);}
    @Override
    @Transactional
    public OrderOtpEntity getOrderByOrderId(long orderId){return userDao.getOrderByOrderId(orderId);}
}
