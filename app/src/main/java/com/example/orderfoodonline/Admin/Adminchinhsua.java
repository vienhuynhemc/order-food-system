package com.example.orderfoodonline.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.ListitemAdmin;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.Server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Adminchinhsua extends AppCompatActivity {
    Spinner spinner;
    public int soloai;
    int idsp,gia;
    String tensp,hinhanh,mota;
    ImageView imageViewHinh;
    EditText tenthemsp,giathemsp,motathemsp,linkanhthemsp;
    Button Addsp,loadanh;
    String tensanpham,giasanpham,motasanpham,linkhinhanhsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminchinhsua);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminchinhsua.this, Admindanhsachsp.class);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.spinnerloai);
        String[] loaisp = new String[]{"Thức Ăn","Thức Uống","Hoa","Bánh Kem"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,loaisp);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                soloai = position +1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        idsp =getIntent().getIntExtra("idsp",-1);
        tensp =getIntent().getStringExtra("tensanpham");
        gia =getIntent().getIntExtra("giasanpham",-1);
        hinhanh =getIntent().getStringExtra("hinhanh");
        mota =getIntent().getStringExtra("mota");

        tenthemsp = findViewById(R.id.tenthemsp);
        giathemsp = findViewById(R.id.giathemsp);
        motathemsp = findViewById(R.id.motathemsp);
        linkanhthemsp = findViewById(R.id.linkanhthemsp);
        imageViewHinh = findViewById(R.id.imageViewHinh);
        loadanh = findViewById(R.id.loadanh);
        Addsp = findViewById(R.id.Addsp);

        tenthemsp.setText(tensp);
        giathemsp.setText(gia+"");
        motathemsp.setText(mota);
        linkanhthemsp.setText(hinhanh);
        Picasso.get().load(linkanhthemsp.getText().toString().trim()).into(imageViewHinh);


        loadanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkhinhanhsanpham = linkanhthemsp.getText().toString().trim();
                if(linkhinhanhsanpham.isEmpty() ) {
                    Toast.makeText(Adminchinhsua.this,"Vui lòng nhập link cho sản phẩm",Toast.LENGTH_SHORT).show();

                }else{
                    linkhinhanhsanpham = linkanhthemsp.getText().toString().trim();
                    Picasso.get().load(linkhinhanhsanpham).into(imageViewHinh);
                }
            }
        });

        Addsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkhinhanhsanpham = linkanhthemsp.getText().toString().trim();
                tensanpham = tenthemsp.getText().toString().trim();
                giasanpham = giathemsp.getText().toString().trim();
                motasanpham = motathemsp.getText().toString().trim();
                linkhinhanhsanpham = linkanhthemsp.getText().toString().trim();
                if(tensanpham.isEmpty() ||giasanpham.isEmpty() || motasanpham.isEmpty() || linkhinhanhsanpham.isEmpty() ){
                    Toast.makeText(Adminchinhsua.this,"Vui lòng nhập dầy đủ thông tin thêm sản phẩm",Toast.LENGTH_SHORT).show();
                }else {
                    Chinhsuasanpham();
                }
            }
        });
    }


    private void Chinhsuasanpham(){
        tensanpham = tenthemsp.getText().toString().trim();
        giasanpham = giathemsp.getText().toString().trim();
        motasanpham = motathemsp.getText().toString().trim();
        linkhinhanhsanpham = linkanhthemsp.getText().toString().trim();
        final RequestQueue requestQueue = Volley.newRequestQueue(Adminchinhsua.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.chinhsuasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(Adminchinhsua.this,"Chỉnh sửa sản phẩm thành công",Toast.LENGTH_SHORT).show();

                    }else  if(success.equals("0")){
                        Toast.makeText(Adminchinhsua.this,"Chỉnh sửa Thất bại ",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idsp", String.valueOf(idsp));
                params.put("tensanpham", tensanpham);
                params.put("giasanpham",giasanpham);
                params.put("motasanpham",motasanpham);
                params.put("linkhinhanhsanpham",linkhinhanhsanpham);
                params.put("loaisp", String.valueOf(soloai));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}
