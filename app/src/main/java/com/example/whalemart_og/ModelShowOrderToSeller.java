package com.example.whalemart_og;

public class ModelShowOrderToSeller {
    String title,time,imageurl,username,date,price;

    public ModelShowOrderToSeller()
    {

    }

    public ModelShowOrderToSeller(String title, String time, String imageurl, String username, String date, String price) {
        this.title = title;
        this.time = time;
        this.imageurl = imageurl;
        this.username = username;
        this.date = date;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
