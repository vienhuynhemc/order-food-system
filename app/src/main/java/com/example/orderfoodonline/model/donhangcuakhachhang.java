package com.example.orderfoodonline.model;

public class donhangcuakhachhang {
    private String Tensp;
    private int Soluong;
    private int idsp,Giatien;

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getGiatien() {
        return Giatien;
    }

    public void setGiatien(int giatien) {
        Giatien = giatien;
    }

    public donhangcuakhachhang(String tensp, int soluong, int idsp, int giatien) {
        Tensp = tensp;
        Soluong = soluong;
        idsp = idsp;
        Giatien = giatien;
    }
}
