package com.example.orderfoodonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.Server.Server;
import com.example.orderfoodonline.adapter.SanPhamAdapter;
import com.example.orderfoodonline.model.Loaisp;
import com.example.orderfoodonline.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodSanPham extends AppCompatActivity {
    RecyclerView listViewsp;
    SanPhamAdapter sanPhamAdapter;
    ArrayList<SanPham> arrlistthucan;
    EditText Timkiem;
    Button btntimkiem;
    int idloaisp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_sanpham);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        truyvan();
        getIdloaisp();
        getData();
        btntimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = Timkiem.getText().toString().trim();
                Intent intent = new Intent(FoodSanPham.this, Timkiemsanpham.class);
                intent.putExtra("timkiem", search);
                startActivity(intent);
            }
        });

    }


    private void getData() {
//        final RequestQueue requestQueue = Volley.newRequestQueue(FoodSanPham.this);
//
//        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanthucan + idloaisp, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject object = response.getJSONObject(i);
//                        arrlistthucan.add(new SanPham(object.getInt("idsp"), object.getString("tensp"), object.getInt("gia"), object.getString("hinhanh"), object.getString("mota"), object.getInt("loaisp"), object.getInt("timestart"), object.getInt("timeend")));
//                        sanPhamAdapter.notifyDataSetChanged();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference root = firebaseDatabase.getReference();
        DatabaseReference trang_chu = root.child("san_pham");
        trang_chu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrlistthucan.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    arrlistthucan.add(new SanPham(Integer.parseInt(d.getKey()),
                            d.child("ten_sp").getValue().toString(),
                            Integer.parseInt(d.child("gia").getValue().toString()),
                            d.child("hinh_anh").getValue().toString(),
                            d.child("mo_ta").getValue().toString(),
                            Integer.parseInt(d.child("loai_sp").getValue().toString()),
                            Integer.parseInt(d.child("time_start").getValue().toString()),
                            Integer.parseInt(d.child("time_end").getValue().toString())
                    ));
                    sanPhamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void getIdloaisp() {
        idloaisp = getIntent().getIntExtra("idloaisp", -1);
    }

    private void truyvan() {
        listViewsp = findViewById(R.id.listview_spthucan);
        Timkiem = findViewById(R.id.timkiensp);
        btntimkiem = findViewById(R.id.buttontk);
        arrlistthucan = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(FoodSanPham.this, arrlistthucan);
        listViewsp.setHasFixedSize(true);
        listViewsp.setLayoutManager(new GridLayoutManager(FoodSanPham.this, 1));
        listViewsp.setAdapter(sanPhamAdapter);
    }
}
