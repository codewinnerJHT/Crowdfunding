package com.example.haitao.crowdfunding.bean;

import java.util.List;

/**
 * Created by haitao on 2020/6/26.
 */

public class UserInfoBean {


    /**
     * msg : 请求成功
     * code : 200
     * user : [{"accttype":"0","address":"四川成都","id":2,"loginacct":"123","username":"11","userpswd":"e10adc3949ba59abbe56e057f20f883e"}]
     */

    private String msg;
    private String code;
    private List<UserBean> user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * accttype : 0
         * address : 四川成都
         * id : 2
         * loginacct : 123
         * username : 11
         * userpswd : e10adc3949ba59abbe56e057f20f883e
         */

        private String accttype;
        private String address;
        private int id;
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

        public int getId() {
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
            return "UserBean{" +
                    "accttype='" + accttype + '\'' +
                    ", address='" + address + '\'' +
                    ", id=" + id +
                    ", loginacct='" + loginacct + '\'' +
                    ", username='" + username + '\'' +
                    ", userpswd='" + userpswd + '\'' +
                    '}';
        }
    }
}
