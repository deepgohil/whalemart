package com.example.whalemart_og;

public class Modelwalktry {
    String ID;
    String address;
    String shopname;
    String time;
    String sAddress;
    String dAddress;
    String productId;
    String imageurl;



    String shopid;
    public Modelwalktry()
    {

    }

    public Modelwalktry(String ID, String address, String shopname, String time, String sAddress, String dAddress, String productId, String imageurl,String  shopid) {
        this.ID = ID;
        this.address = address;
        this.shopname = shopname;
        this.time = time;
        this.sAddress = sAddress;
        this.dAddress = dAddress;
        this.productId = productId;
        this.imageurl = imageurl;
        this.shopid = shopid;
    }
    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
