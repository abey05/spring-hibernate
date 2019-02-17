package com.hibernate.service;

import com.hibernate.model.UserEvent;
import com.hibernate.model.PassbookEvent;
import com.hibernate.model.UserRoles;
import com.hibernate.model.Users;
import java.util.List;

public interface UserService {
    List<UserEvent> findAllUsers();
    void addUser(UserEvent user);
    void addUsers(Users users);
    void addUserRoles(UserRoles userRoles);
    void Update(UserEvent user);
    void UpdateUsers(Users users);
    void UpdateUserRoles(UserRoles roles);
    void deleteUser(long id);
    void deleteUsers(String username);
    void addWalletBalance(long id, double balance);
    boolean debitWalletBalance(long id, double balance);
    UserEvent getUserById(long id);
    void addTransactionDetail(PassbookEvent passbookEvent);
    List<PassbookEvent> showPassbook(long userId, String type);
    UserEvent getUserByUsername(String username);
    UserEvent getUserByMobileNo(String mobileNo);
    UserRoles getUserRolesByUsername(String username);
}
