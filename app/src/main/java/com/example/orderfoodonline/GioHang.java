package com.example.orderfoodonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfoodonline.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GioHang extends AppCompatActivity {
    ListView listviewgiohang;
    public static TextView thongbao,tongtien;
    Button btnthanhtoangiohang,btntieptucmuahang;
    public static GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHang.this, MainActivity.class);
                startActivity(intent);//Start the same Activity
                            }
        });
        Truyxuatid();
        Checkgiohang();
        Tongtien();
        Eventtieptucmuahang();
        EventThanhtoanmuahang();

    }

    private void Eventtieptucmuahang() {
        btntieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHang.this, MainActivity.class);
                startActivity(intent);//Start the same Activity

            }
        });
    }


    private void EventThanhtoanmuahang() {
        btnthanhtoangiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(MainActivity.mangGioHang.size()>0){
                   Intent intent = new Intent(GioHang.this, ThongTinKhachHang.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(GioHang.this, "VUI LÒNG CHỌN SẢN PHẪM VÀO GIỎ TRƯỚC KHI THANH TOÁN", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    private void Truyxuatid() {
        listviewgiohang = findViewById(R.id.listviewgiohang);
        thongbao = findViewById(R.id.thongbao);
        tongtien = findViewById(R.id.tongtien);
        btnthanhtoangiohang = findViewById(R.id.btnthanhtoangiohang);
        btntieptucmuahang = findViewById(R.id.btntieptucmuahang);
        gioHangAdapter  = new GioHangAdapter(GioHang.this,MainActivity.mangGioHang);
        listviewgiohang.setAdapter(gioHangAdapter);
    }

    private void Checkgiohang() {
        if(MainActivity.mangGioHang.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            thongbao.setVisibility(View.VISIBLE);
            listviewgiohang.setVisibility(View.GONE);

        }
        else{
            gioHangAdapter.notifyDataSetChanged();
            thongbao.setVisibility(View.GONE);
            listviewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void Tongtien() {
        long tongtienthanhtoan=0;
        for(int i=0;i<MainActivity.mangGioHang.size();i++){
            tongtienthanhtoan+=MainActivity.mangGioHang.get(i).getGiatong();
        }
        DecimalFormat decimalFomat=new DecimalFormat("###,###,###");
        tongtien.setText(decimalFomat.format(tongtienthanhtoan)+" Đ");
    }


}
