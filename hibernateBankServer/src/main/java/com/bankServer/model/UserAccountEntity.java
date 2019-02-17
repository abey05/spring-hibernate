package com.bankServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="USER_ACCOUNT")
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_NO")
    private long accountNo;

    @Column(name="BALANCE")
    private double balance;

    @Column(name="createdAt",updatable =false)
    //@CreationTimestamp
    private Timestamp createdAt;

    @Column(name="updatedAt")
    //@UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "NAME")
    private String name;

    public UserAccountEntity(){}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public long getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(long accountNo){
        this.accountNo=accountNo;
    }
    public Timestamp getCreatedAt(){return createdAt;}
    public Timestamp getUpdatedAt(){return updatedAt;}
    public double getBalance(){return balance;}
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
