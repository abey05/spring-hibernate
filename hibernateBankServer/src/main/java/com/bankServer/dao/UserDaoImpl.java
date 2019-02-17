package com.bankServer.dao;

import com.bankServer.model.OrderOtpEntity;
import com.bankServer.model.UserAccountEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository

public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(UserAccountEntity userAccountEntity){
        sessionFactory.getCurrentSession().save(userAccountEntity);
    }
    @Override
    @Transactional
    public void update(UserAccountEntity userAccountEntity){sessionFactory.getCurrentSession().saveOrUpdate(userAccountEntity);}
    @Override
    @Transactional
    public void deleteAccount(long accountNo){
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(UserAccountEntity.class,accountNo));
    }
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<UserAccountEntity> listUsers(){
        return sessionFactory.getCurrentSession().createQuery("from UserAccountEntity").list();
    }
    @Override
    @Transactional
    public boolean deductMoney(long accountNo, double balance){
        UserAccountEntity userAccountEntity = sessionFactory.getCurrentSession().get(UserAccountEntity.class,accountNo);
        if(userAccountEntity.getBalance() > balance){
            userAccountEntity.setBalance(userAccountEntity.getBalance()-balance);
            this.sessionFactory.getCurrentSession().saveOrUpdate(userAccountEntity);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public UserAccountEntity getUserByAccountNo(long accountNo){
        return sessionFactory.getCurrentSession().get(UserAccountEntity.class,accountNo);
    }
    @Override
    @Transactional
    public boolean isAccount(long accountNo){
        if(sessionFactory.getCurrentSession().get(UserAccountEntity.class,accountNo) != null)
            return true;
        return false;
    }
    /*Otp*/
    public Long addOtp(OrderOtpEntity orderOtpEntity){
       return (Long) sessionFactory.getCurrentSession().save(orderOtpEntity);
    }
    public void updateOtpEntity(OrderOtpEntity orderOtpEntity){
        sessionFactory.getCurrentSession().saveOrUpdate(orderOtpEntity);
    }
    public OrderOtpEntity getOrderByOrderId(long orderId){
        return sessionFactory.getCurrentSession().get(OrderOtpEntity.class,orderId);
    }
}
