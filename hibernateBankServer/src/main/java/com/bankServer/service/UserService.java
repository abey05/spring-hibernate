package com.bankServer.service;

import com.bankServer.model.OrderOtpEntity;
import com.bankServer.model.UserAccountEntity;

import java.util.List;

public interface UserService {
    void add(UserAccountEntity userAccountEntity);
    void update(UserAccountEntity userAccountEntity);
    List<UserAccountEntity> listUsers();
    UserAccountEntity getUserByAccountNo(long accountNo);
    boolean deductMoney(long accountNo , double balance);
    void deleteAccount(long accountNo);
    boolean isAccount(long accountNo);

    Long addOtp(OrderOtpEntity orderOtpEntity);
    OrderOtpEntity getOrderByOrderId(long orderId);
    void updateOtpEntity(OrderOtpEntity orderOtpEntity);

}

