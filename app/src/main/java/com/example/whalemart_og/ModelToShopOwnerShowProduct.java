package com.example.whalemart_og;

public class ModelToShopOwnerShowProduct {
    String IDfordeleteproduct,Title,catagory,img1,realprice;
    public ModelToShopOwnerShowProduct ()
    {

    }

    public ModelToShopOwnerShowProduct(String IDfordeleteproduct, String title, String catagory, String img1, String realprice) {
        this.IDfordeleteproduct = IDfordeleteproduct;
        Title = title;
        this.catagory = catagory;
        this.img1 = img1;
        this.realprice = realprice;
    }

    public String getIDfordeleteproduct() {
        return IDfordeleteproduct;
    }

    public void setIDfordeleteproduct(String IDfordeleteproduct) {
        this.IDfordeleteproduct = IDfordeleteproduct;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getRealprice() {
        return realprice;
    }

    public void setRealprice(String realprice) {
        this.realprice = realprice;
    }
}
