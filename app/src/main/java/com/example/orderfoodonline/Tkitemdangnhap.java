package com.example.orderfoodonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.orderfoodonline.Admin.Admindanhsachsp;
import com.example.orderfoodonline.Admin.Admindanhsachtaikhoan;
import com.example.orderfoodonline.Admin.Adminthemsanpham;
import com.example.orderfoodonline.adapter.ItemdangnhApadapter;
import com.example.orderfoodonline.model.Itemdangnhap;

import java.util.ArrayList;

public class Tkitemdangnhap extends AppCompatActivity {
    ListView listitemdangnhap;
    ArrayList<Itemdangnhap> arrayList;
    ItemdangnhApadapter apadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_itemdangnhap);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tkitemdangnhap.this, MainActivity.class);
                startActivity(intent);//Start the same Activity
            }
        });

        listitemdangnhap = findViewById(R.id.listitemdangnhap);
        arrayList = new ArrayList<Itemdangnhap>();
        arrayList.add(new Itemdangnhap("Đăng Xuất","https://img.icons8.com/nolan/2x/exit.png"));
        arrayList.add(new Itemdangnhap("Đơn Hàng","https://img.icons8.com/cotton/2x/book.png"));



        apadapter = new ItemdangnhApadapter(this,R.layout.item_dangnhap,arrayList);
        listitemdangnhap.setAdapter(apadapter);

        listitemdangnhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(Tkitemdangnhap.this, Thongtintaikhoan.class);
                        intent.putExtra("Dangnhapthanhcong", 0);
                        startActivity(intent);
                    }
                    break;
                    case 1: {
                        Intent intent = new Intent(Tkitemdangnhap.this, Donhancuasanpham.class);
                        intent.putExtra("Dangnhapthanhcong", 0);
                        startActivity(intent);
                    }
                    break;
                }

            }
        });

    }
}
