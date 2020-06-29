package com.example.haitao.crowdfunding.bean;

import java.io.Serializable;

/**
 * Created by haitao on 2020/6/22.
 */

public class GoodsInfoBean implements Serializable{
    private  String imageurl;//图片地址
    private String name;//项目名
    private String information;//项目信息
    private  Double price;//单价
    private String status;//状态
    private Integer daysrmaining;
    private  double money;
    private double suppormoney;
    private Integer id;
    private String phone;
    private Integer uid;
    private String briefintroduction;

    public String getBriefintroduction() {
        return briefintroduction;
    }

    public void setBriefintroduction(String briefintroduction) {
        this.briefintroduction = briefintroduction;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDaysrmaining() {
        return daysrmaining;
    }

    public void setDaysrmaining(Integer daysrmaining) {
        this.daysrmaining = daysrmaining;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getSuppormoney() {
        return suppormoney;
    }

    public void setSuppormoney(double suppormoney) {
        this.suppormoney = suppormoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "GoodsInfoBean{" +
                "imageurl='" + imageurl + '\'' +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", daysrmaining=" + daysrmaining +
                ", money=" + money +
                ", suppormoney=" + suppormoney +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", uid=" + uid +
                '}';
    }
}
