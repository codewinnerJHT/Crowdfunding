package com.example.haitao.crowdfunding.bean;

import java.util.List;

/**
 * Created by haitao on 2020/6/21.
 */

public class ProjectBeanData {

    /**
     * msg : 请求成功
     * gethost : [{"briefintroduction":"机器人可以做家务，可以安全报警","day":60,"daysremaining":60,"hot":1,"id":1,"imageurl":"/1447232577216.jpg","information":"目前我设计的辅助产品有：完成刷碗、洗衣、收拾餐桌、收拾床、取来送走特定的物品等等诸多相应的辅助产品（以供参考），来达到让机器人在完成家庭事物过程中的最简单化、多远化，为机器人走进普通家庭奠定基础。可以利用这个模式，让扫地机器人来完成更多家务事。还可利用机器人的性能完成家庭安全报警以及对老年人的自助、他助、自救等等的一些家庭应用，给家居生活带来实实在在方便，而被更多的人认可，以便能推广使用，从而能降低成本，来改变现有扫地机器人的现状","money":1.0E7,"name":"科创板机器人项目","status":"0","supportmoney":3000000,"userid":1},{"briefintroduction":"为家庭提供更加清新的空气","day":90,"daysremaining":45,"hot":1,"id":2,"imageurl":"/1477984921265.jpg","information":"空气净化器主要由马达、风扇、空气过滤网等系统组成，其工作原理为：机器内的马达和风扇使室内空气循环流动，污染的空气通过机内的空气过滤网后将各种污染物清除或吸附，将空气不断电离，产生大量负离子，被微风扇送出，形成负离子气流，达到清洁、净化空气的目的。","money":2.0E7,"name":"沁心空气进化器","status":"0","supportmoney":4000000,"userid":2}]
     * code : 200
     * bannerurl : [{"bannerurl":"/1478770583834.png","id":1},{"bannerurl":"/1478770583835.png","id":2},{"bannerurl":"/1478770583836.png","id":3}]
     */

    private String msg;
    private String code;
    private List<GethostBean> gethost;
    private List<BannerurlBean> bannerurl;

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

    public List<GethostBean> getGethost() {
        return gethost;
    }

    public void setGethost(List<GethostBean> gethost) {
        this.gethost = gethost;
    }

    public List<BannerurlBean> getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(List<BannerurlBean> bannerurl) {
        this.bannerurl = bannerurl;
    }

    public static class GethostBean {
        /**
         * briefintroduction : 机器人可以做家务，可以安全报警
         * day : 60
         * daysremaining : 60
         * hot : 1
         * id : 1
         * imageurl : /1447232577216.jpg
         * information : 目前我设计的辅助产品有：完成刷碗、洗衣、收拾餐桌、收拾床、取来送走特定的物品等等诸多相应的辅助产品（以供参考），来达到让机器人在完成家庭事物过程中的最简单化、多远化，为机器人走进普通家庭奠定基础。可以利用这个模式，让扫地机器人来完成更多家务事。还可利用机器人的性能完成家庭安全报警以及对老年人的自助、他助、自救等等的一些家庭应用，给家居生活带来实实在在方便，而被更多的人认可，以便能推广使用，从而能降低成本，来改变现有扫地机器人的现状
         * money : 1.0E7
         * name : 科创板机器人项目
         * status : 0
         * supportmoney : 3000000.0
         * userid : 1
         */

        private String briefintroduction;
        private int day;
        private int daysremaining;
        private int hot;
        private int id;
        private String imageurl;
        private String information;
        private double money;
        private String name;
        private String status;
        private double supportmoney;
        private int userid;
        private double price;
        private  String phone;

        public String getBriefintroduction() {
            return briefintroduction;
        }

        public void setBriefintroduction(String briefintroduction) {
            this.briefintroduction = briefintroduction;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getDaysremaining() {
            return daysremaining;
        }

        public void setDaysremaining(int daysremaining) {
            this.daysremaining = daysremaining;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getSupportmoney() {
            return supportmoney;
        }

        public void setSupportmoney(double supportmoney) {
            this.supportmoney = supportmoney;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
        public  double getPrice(){
            return price;
        }
        public void setPrice(double price){
            this.price=price;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class BannerurlBean {
        /**
         * bannerurl : /1478770583834.png
         * id : 1
         */

        private String bannerurl;
        private int id;

        public String getBannerurl() {
            return bannerurl;
        }

        public void setBannerurl(String bannerurl) {
            this.bannerurl = bannerurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
