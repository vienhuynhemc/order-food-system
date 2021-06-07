package com.example.orderfoodonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.Admin.Admindanhsachsp;
import com.example.orderfoodonline.Server.Server;
import com.example.orderfoodonline.adapter.Adapterdonhangcuakhachhang;
import com.example.orderfoodonline.model.SanPham;
import com.example.orderfoodonline.model.donhang;
import com.example.orderfoodonline.model.donhangcuakhachhang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.orderfoodonline.ThongTinKhachHang.Madonhangkhachhang;

public class Donhancuasanpham extends AppCompatActivity {
    String Tensp= "";
    int Soluong=0;
    int Giatien =0;
    int idsp =0;

    Adapterdonhangcuakhachhang adapterdonhangcuakhachhang;
    ArrayList<donhangcuakhachhang>arrayList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhancuasanpham);
        getdanhsachsanpham();
        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        adapterdonhangcuakhachhang = new Adapterdonhangcuakhachhang(this,R.layout.row_donhang_khachhang,arrayList);
        listView.setAdapter(adapterdonhangcuakhachhang);


    }

    private void getdanhsachsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(Donhancuasanpham.this);
        if(getIntent().getIntExtra("madonhang",-1) != -1){
            Madonhangkhachhang = String.valueOf(getIntent().getIntExtra("madonhang",-1));
        }
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.donhangcuakhanhhang+"?Madonhang="+Madonhangkhachhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Tensp = object.getString("Tensp");
                        Soluong = object.getInt("Soluong");
                        Giatien =object.getInt("Giatien");
                        idsp = object.getInt("idsp");
                        Log.e("TAG", "onResponse: "+idsp );
                        arrayList.add(new donhangcuakhachhang(Tensp,Soluong,idsp,Giatien));
                        adapterdonhangcuakhachhang.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
