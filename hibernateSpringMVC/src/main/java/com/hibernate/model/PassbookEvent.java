package com.hibernate.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="USER_PASSBOOK")
public class PassbookEvent {
    @Id
    @Column(name="txnId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnId;

    @Column(name="userId")
    private long userId;

    @Column(name="debitAccount")
    private long debitAccount;

    @Column(name = "creditWallet")
    private String creditWallet;

    @Column(name="amount")
    private double amount;

    @Column(name="type")
    private String type;

    @Column(name="timestamp")
    private Timestamp timestamp;

    public  PassbookEvent(){}
    public long getTxnId(){return txnId;}
    public void setTxnId(long txnId){this.txnId=txnId;}
    public long getUserId(){return userId;}
    public long getDebitAccount(){return debitAccount;}
    public String getType(){return type;}
    public void setType(String type){this.type=type;}
    public void setDebitAccount(long debitAccount){this.debitAccount= debitAccount;}
    public double getAmount(){return amount;}
    public void setAmount(double amount){this.amount=amount;}
    public String getCreditWallet(){return creditWallet;}
    public void setCreditWallet(String creditWallet){this.creditWallet=creditWallet;}
    public Timestamp getTimestamp(){return timestamp;}
    public void setTimestamp (Timestamp timestamp){this.timestamp = timestamp;}
    public void setUserId(long userId){this.userId=userId;}
}
