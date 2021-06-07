package com.example.orderfoodonline.model;

public class donhang {
    String Ten,Diachi;
    int Sodienthoai,Madonhang;

    public donhang(String ten, String diachi, int sodienthoai, int madonhang) {
        Ten = ten;
        Diachi = diachi;
        Sodienthoai = sodienthoai;
        Madonhang = madonhang;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public int getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        Sodienthoai = sodienthoai;
    }

    public int getMadonhang() {
        return Madonhang;
    }

    public void setMadonhang(int madonhang) {
        Madonhang = madonhang;
    }
}
