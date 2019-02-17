package com.hibernate.dao;
import java.util.List;
import com.hibernate.model.PassbookEvent;
import com.hibernate.model.UserRoles;
import com.hibernate.model.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.hibernate.model.UserEvent;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory  sessionFactory;
    @Override
    @Transactional
    public void addUser(UserEvent user){
        this.sessionFactory.getCurrentSession().save(user);
    }
    @Override
    @Transactional
    public void addUsers(Users users){this.sessionFactory.getCurrentSession().save(users);}
    @Override
    @Transactional
    public void addUserRoles(UserRoles userRoles){this.sessionFactory.getCurrentSession().save(userRoles);}
    @Override
    @Transactional
    public void Update(UserEvent user){
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
    @Override
    @Transactional
    public void UpdateUsers(Users users){this.sessionFactory.getCurrentSession().saveOrUpdate(users);}
    @Override
    @Transactional
    public void UpdateUserRole(UserRoles roles){this.sessionFactory.getCurrentSession().saveOrUpdate(roles);}
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<UserEvent> findAllUsers(){
        return this.sessionFactory.getCurrentSession().createQuery("from UserEvent").list(); 
    }
    @Override
    @Transactional
    public void deleteUser(long id){
        if(sessionFactory.getCurrentSession().load(UserEvent.class,id) != null) {
            sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(UserEvent.class,id)); }
    }
    @Override
    @Transactional
    public void deleteUsers(String username){
        if(sessionFactory.getCurrentSession().load(Users.class,username) != null)
            sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(Users.class,username));
    }
    @Override
    @Transactional
    public void addWalletBalance(long id, double balance) {
        UserEvent user = sessionFactory.getCurrentSession().load(UserEvent.class,id);
        user.setWalletBalance(user.getWalletBalance()+ balance);
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
    @Override
    @Transactional
    public boolean debitWalletBalance(long id, double balance){
        UserEvent user = sessionFactory.getCurrentSession().load(UserEvent.class,id);
        if(user.getWalletBalance() < balance)
            return false;
        user.setWalletBalance(user.getWalletBalance() - balance);
        return true;
    }
    @Override
    @Transactional
    public UserEvent getUserById(long id){
        return sessionFactory.getCurrentSession().get(UserEvent.class,id);
    }
    @Override
    @Transactional
    public void addTransactionDetail(PassbookEvent passbookEvent){
        sessionFactory.getCurrentSession().save(passbookEvent);
    }
    @Override
    @Transactional
    //@Query(value="SELECT * FROM USER_PASSBOOK WHERE userId= ?1", nativeQuery = true)
    public List<PassbookEvent> showPassbook(long userId, String type){
        return sessionFactory.getCurrentSession().createQuery("from PassbookEvent WHERE userId=:userId and type=:type").setParameter("userId",userId).setParameter("type",type).list();
    }
    @Override
    @Transactional
    public UserEvent getUserByUsername(String username){
        return (UserEvent) sessionFactory.getCurrentSession().createQuery("from UserEvent where username=:username").setParameter("username",username).uniqueResult();
    }
    @Override
    @Transactional
    public UserEvent getUserByMobileNo(String mobileNo){
        return (UserEvent) sessionFactory.getCurrentSession().createQuery("from UserEvent where mobileNo=:mobileNo").setParameter("mobileNo",mobileNo).uniqueResult();
    }
    @Override
    @Transactional
    public UserRoles getUserRolesByUsername(String username){
        return (UserRoles)sessionFactory.getCurrentSession().createQuery("from UserRoles where username=:username").setParameter("username",username).uniqueResult();
    }
}

