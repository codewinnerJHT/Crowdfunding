package com.example.haitao.crowdfunding.bean;

import java.io.Serializable;

/**
 * Created by haitao on 2020/6/26.
 */

public class UserDetaileBean implements Serializable {
    private String accttype;
    private String address;
    private Integer id;
    private String loginacct;
    private String username;
    private String userpswd;

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    @Override
    public String toString() {
        return "UserDetaileBean{" +
                "accttype='" + accttype + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", username='" + username + '\'' +
                ", userpswd='" + userpswd + '\'' +
                '}';
    }
}
