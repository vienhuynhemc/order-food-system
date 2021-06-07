package com.example.orderfoodonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.orderfoodonline.Admin.Admindanhsachdonhang;
import com.example.orderfoodonline.Admin.Admindanhsachsp;
import com.example.orderfoodonline.Admin.Admindanhsachtaikhoan;
import com.example.orderfoodonline.Admin.Adminthemsanpham;
import com.example.orderfoodonline.adapter.ItemdangnhApadapter;
import com.example.orderfoodonline.model.Itemdangnhap;

import java.util.ArrayList;

public class ListitemAdmin extends AppCompatActivity {
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
                Intent intent = new Intent(ListitemAdmin.this, MainActivity.class);
                startActivity(intent);//Start the same Activity
            }
        });

        listitemdangnhap = findViewById(R.id.listitemdangnhap);
        arrayList = new ArrayList<Itemdangnhap>();
        arrayList.add(new Itemdangnhap("Danh Sách Sản Phẩm","https://img.icons8.com/plasticine/2x/checklist.png"));
        arrayList.add(new Itemdangnhap("Danh Sách Tài Khoản","https://img.icons8.com/dusk/2x/administrator-male.png"));
        arrayList.add(new Itemdangnhap("Danh Sách Đơn Hàng","https://img.icons8.com/cotton/2x/book.png"));
        arrayList.add(new Itemdangnhap("Thêm Sản Phẩm","https://img.icons8.com/color/2x/plus.png"));
        arrayList.add(new Itemdangnhap("Đăng Xuất","https://img.icons8.com/nolan/2x/exit.png"));



        apadapter = new ItemdangnhApadapter(this,R.layout.item_dangnhap,arrayList);
        listitemdangnhap.setAdapter(apadapter);

        listitemdangnhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(ListitemAdmin.this, Admindanhsachsp.class);
                            startActivity(intent);
                        }
                        break;
                    case 1:{
                        Intent intent = new Intent(ListitemAdmin.this, Admindanhsachtaikhoan.class);
                        startActivity(intent);
                    }
                    break;
                    case 2: {
                        Intent intent = new Intent(ListitemAdmin.this, Admindanhsachdonhang.class);
                        startActivity(intent);
                    }
                    break;
                    case 3: {
                        Intent intent = new Intent(ListitemAdmin.this, Adminthemsanpham.class);
                        startActivity(intent);
                    }
                        break;
                    case 4: {
                        Intent intent = new Intent(ListitemAdmin.this, Thongtintaikhoan.class);
                        intent.putExtra("Dangnhapthanhcong", 0);
                        startActivity(intent);
                    }
                    break;
                }

            }
        });

    }

}


