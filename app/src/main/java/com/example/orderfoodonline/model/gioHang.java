package com.example.orderfoodonline.model;

public class gioHang {
    public int idsp;
    public String tensp;
    public int giasp;
    public long giatong;
    public String hinhsp;
    public int soluongsp;

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public long getGiatong() {
        return giatong;
    }

    public void setGiatong(long giatong) {
        this.giatong = giatong;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public gioHang() {

    }

    public gioHang(int idsp, String tensp, int giasp, String hinhsp, int soluongsp, long giatong) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
        this.giatong = giatong;

    }


}

