package com.example.orderfoodonline.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.Server.Server;
import com.example.orderfoodonline.adapter.DanhsachdonhangAdapter;
import com.example.orderfoodonline.model.donhang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Admindanhsachdonhang extends AppCompatActivity {
    ListView danhsachdonhang;
    ArrayList<donhang> Listdanhsachdonhang;
    DanhsachdonhangAdapter danhsachdonhangAdapter;
    String tenkh= "";
    String diachi= "";
    int madonhang =0;
    int sdt =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindanhsachdonhang);

        danhsachdonhang = findViewById(R.id.lsdanhsachdonhang);
        Listdanhsachdonhang = new ArrayList<>();
        danhsachdonhangAdapter = new DanhsachdonhangAdapter(this,R.layout.row_donhang,Listdanhsachdonhang);
        danhsachdonhang.setAdapter(danhsachdonhangAdapter);
        getdanhsachsanpham();
    }

    private void getdanhsachsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(Admindanhsachdonhang.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.danhsachdonhang, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        tenkh = object.getString("Tenkhachhang");
                        diachi = object.getString("Diachi");
                        sdt =object.getInt("Sodienthoai");
                        madonhang = object.getInt("Madonhang");
                        Listdanhsachdonhang.add(new donhang(tenkh,diachi,sdt,madonhang));
                        Log.e("TAG", "onResponse:"+tenkh);
                        danhsachdonhangAdapter.notifyDataSetChanged();
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
