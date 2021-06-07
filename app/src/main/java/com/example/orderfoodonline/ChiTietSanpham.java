package com.example.orderfoodonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfoodonline.model.gioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static com.example.orderfoodonline.Thongtintaikhoan.Dangnhapthanhcong;

public class ChiTietSanpham extends AppCompatActivity {
    int gia,id;
    String tensp,hinhanh,mota;
    Button btnmua;
    ImageView imgchitietsanpham;
    TextView tenchitietsanpham,giachitietsanpham,motachitietsanpham;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sanpham);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        id =getIntent().getIntExtra("id",-1);
        tensp =getIntent().getStringExtra("tensp");
        gia =getIntent().getIntExtra("gia",-1);
        hinhanh =getIntent().getStringExtra("hinhanh");
        mota =getIntent().getStringExtra("mota");

        spinner = findViewById(R.id.spinner);
        tenchitietsanpham = findViewById(R.id.tenchitietsanpham);
        giachitietsanpham = findViewById(R.id.giachitietsanpham);
        motachitietsanpham = findViewById(R.id.motachitietsanpham);
        imgchitietsanpham  = findViewById(R.id.imgchitietsanpham);
        btnmua = findViewById(R.id.btnmua);
        tenchitietsanpham.setText(tensp);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        giachitietsanpham.setText("Giá: "+decimalFormat.format(gia)+" Đồng");
        motachitietsanpham.setText(mota);
        Picasso.get().load(hinhanh).into(imgchitietsanpham);
        chonspinnersoluong();
            ThemgGioHang();

    }

    private void ThemgGioHang() {
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Dangnhapthanhcong==0){
                    Toast.makeText(ChiTietSanpham.this,"Vui Lòng đăng nhập trước khi mua hàng",Toast.LENGTH_LONG).show();
                }else {
                    if (MainActivity.mangGioHang.size() > 0) {
                        int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                        boolean exsist = false;
                        for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                            if (MainActivity.mangGioHang.get(i).getIdsp() == id) {
                                MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp() + sl);
                                if (MainActivity.mangGioHang.get(i).getSoluongsp() >= 10) {
                                    MainActivity.mangGioHang.get(i).setSoluongsp(10);
                                }
                                MainActivity.mangGioHang.get(i).setGiasp(gia * sl + MainActivity.mangGioHang.get(i).getGiasp());
                                exsist = true;
                            }
                        }
                        if (exsist == false) {
                            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                            long giatong = soluong * gia;
                            MainActivity.mangGioHang.add(new gioHang(id, tensp, gia, hinhanh, soluong, giatong));
                        }
                    } else {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giatong = soluong * gia;
                        MainActivity.mangGioHang.add(new gioHang(id, tensp, gia, hinhanh, soluong, giatong));

                    }
                    Intent intent = new Intent(ChiTietSanpham.this, GioHang.class);
                    startActivity(intent);
                }
            }
        });
    }

    private  void chonspinnersoluong(){
        Integer[] Soluong = new Integer[]{1,2,3,4,5,6,7,8,9};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,Soluong);
        spinner.setAdapter(arrayAdapter);

    }
}
