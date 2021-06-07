package com.example.orderfoodonline.model;

public class Itemdangnhap{
    public String Ten,Hinh;

    public Itemdangnhap() {

    }

    public Itemdangnhap( String Ten, String Hinh) {
        this.Ten = Ten;
        this.Hinh = Hinh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
}
