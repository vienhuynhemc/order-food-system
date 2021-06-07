package com.example.orderfoodonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.Server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {
    EditText sdt,diachi,tenkh;
    Button btnxacnhan,btnquaylai;
    public static String Madonhangkhachhang ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        sdt = findViewById(R.id.sdtdonhang);
        diachi = findViewById(R.id.diachidonhang);
        tenkh = findViewById(R.id.tenkhachhang);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        btnquaylai = findViewById(R.id.btnquaylai);
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongtinkhachhang();
            }
        });


    }


    private void thongtinkhachhang() {
                final String sodienthoai = sdt.getText().toString().trim();
                final String diachikhachhang = diachi.getText().toString().trim();
                final String tenkhachhang = tenkh.getText().toString().trim();
                if(tenkhachhang.length()>0&&sodienthoai.length()>0&&diachikhachhang.length()>0){
                    final RequestQueue requestQueue= Volley.newRequestQueue(ThongTinKhachHang.this);
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.thongtindonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            Madonhangkhachhang = madonhang;
                            if(!madonhang.isEmpty()){
                                final RequestQueue requestQueue1= Volley.newRequestQueue(ThongTinKhachHang.this);
                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.chitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(!response.isEmpty()){
                                            MainActivity.mangGioHang.clear();
                                            Toast.makeText(ThongTinKhachHang.this,"Xác nhận đơn hàng thành công",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ThongTinKhachHang.this,MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.mangGioHang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.mangGioHang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String>hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String>hashMap=new HashMap<String, String>();
                            hashMap.put("sdtkh", sodienthoai);
                            hashMap.put("diachikh",diachikhachhang);
                            hashMap.put("tenkh",tenkhachhang);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(ThongTinKhachHang.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
}


