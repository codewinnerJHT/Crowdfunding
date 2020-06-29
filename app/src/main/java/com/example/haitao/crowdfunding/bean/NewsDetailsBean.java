package com.example.haitao.crowdfunding.bean;

import java.io.Serializable;

/**
 * Created by haitao on 2020/6/25.
 */

public class NewsDetailsBean  implements Serializable{
    private int comment;
    private String contentone;
    private String contentthree;
    private String contenttwo;
    private int id;
    private String imageurlone;
    private String imageurlthree;
    private String imageurltwo;
    private String publisher;
    private String time;
    private String title;
    private int type;

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getContentone() {
        return contentone;
    }

    public void setContentone(String contentone) {
        this.contentone = contentone;
    }

    public String getContentthree() {
        return contentthree;
    }

    public void setContentthree(String contentthree) {
        this.contentthree = contentthree;
    }

    public String getContenttwo() {
        return contenttwo;
    }

    public void setContenttwo(String contenttwo) {
        this.contenttwo = contenttwo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurlone() {
        return imageurlone;
    }

    public void setImageurlone(String imageurlone) {
        this.imageurlone = imageurlone;
    }

    public String getImageurlthree() {
        return imageurlthree;
    }

    public void setImageurlthree(String imageurlthree) {
        this.imageurlthree = imageurlthree;
    }

    public String getImageurltwo() {
        return imageurltwo;
    }

    public void setImageurltwo(String imageurltwo) {
        this.imageurltwo = imageurltwo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
