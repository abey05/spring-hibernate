package com.hibernate.model;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name= "USER_EVENT")
public class UserEvent {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="username")
    private String username;

    @Column(name= "password")
    private String password;

    @Column(name="wallet")
    private double walletBalance;

    @Column(name="mobileNo")
    private String mobileNo;

    @Column(name="createdAt",updatable = false)
    private Timestamp createdAt;

    @Column(name="updatedAt")
    private  Timestamp updatedAt;

    public UserEvent() { }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getName(){ return name;}
    public double getWalletBalance(){return walletBalance;}
    public void setWalletBalance(double balance){this.walletBalance=balance;}
    public void setName(String name){
        this.name= name;
    }
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getPassword(){return password;}
    public  void setPassword(String password){this.password = password;}
    public Timestamp getCreatedAt(){return createdAt;}
    public Timestamp getUpdatedAt(){return updatedAt;}
    public String getMobileNo(){return mobileNo;}
    public void setMobileNo(String mobileNo){this.mobileNo=mobileNo;}
    public void setUpdatedAt(Timestamp timestamp){updatedAt=timestamp;}
}
