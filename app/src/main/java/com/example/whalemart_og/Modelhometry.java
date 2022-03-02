package com.example.whalemart_og;

public class Modelhometry {
    String productId;
    String title;
    String sAddress;
    String time;
    String orderId;
    String imageurl;
    String size;
    String price;
    String desc;
    String shopid;
    public Modelhometry()
    {

    }

    public Modelhometry(String productId, String title, String sAddress, String time, String orderId, String imageurl, String size, String price, String desc, String shopid) {
        this.productId = productId;
        this.title = title;
        this.sAddress = sAddress;
        this.time = time;
        this.orderId = orderId;
        this.imageurl = imageurl;
        this.size = size;
        this.price = price;
        this.desc = desc;
        this.shopid = shopid;
    }
    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
