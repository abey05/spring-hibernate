package com.bankServer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="USER_OTP_ENTITY")
public class OrderOtpEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private long orderId;

    @Column(name="userId")
    private long userId;

    @Column(name="amount")
    private double amount;

    @Column(name="accountNo")
    private long accountNo;
    @Column(name="otp")
    private long otp;
    @Column(name="timestamp")
    private Timestamp timestamp;
    public OrderOtpEntity(){}

    public long getOrderId() {return orderId;}
    public long getUserId() {return userId; }
    public double getAmount() {return amount;}
    public long getAccountNo() {return accountNo;}
    public long getOtp(){return otp;}
    public Timestamp getTimestamp() {return timestamp;}
    public void setOrderId(long orderId) {this.orderId = orderId;}
    public void setUserId(long userId) {this.userId = userId;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setAccountNo(long accountNo) {this.accountNo = accountNo;}
    public void setOtp(long otp){this.otp=otp;}
    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
}
