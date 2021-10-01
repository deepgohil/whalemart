package com.example.whalemart_og;

public class Modelhistory {
    String desc,id,price,productcount,size,title,url1;
    public Modelhistory()
    {

    }

    public Modelhistory(String desc, String id, String price, String productcount, String size, String title, String url1) {
        this.desc = desc;
        this.id = id;
        this.price = price;
        this.productcount = productcount;
        this.size = size;
        this.title = title;
        this.url1 = url1;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
}
