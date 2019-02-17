package com.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name="USER_ROLES", uniqueConstraints = @UniqueConstraint(columnNames = {"role", "username"}))

public class UserRoles {
    @Id
    @Column(name="user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRoleId;

    @Column(name="username")
    private String username;

    @Column(name="role")
    private String role;

    public UserRoles(){}
    public long getUserRoleId(){return userRoleId;}
    public void setUserRoleId(long userRoleId){this.userRoleId = userRoleId;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getRole(){return role;}
    public void setRole(String role){this.role=role;}
}
