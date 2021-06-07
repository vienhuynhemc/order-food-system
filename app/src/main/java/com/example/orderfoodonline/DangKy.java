package com.example.orderfoodonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.Server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKy extends AppCompatActivity {
    EditText sdt,mk1,mk2;
    Button btndangky,btndangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();
            }
        });
        sdt = findViewById(R.id.sdtdonhang);
        mk1 = findViewById(R.id.mk1);
        mk2 = findViewById(R.id.mk2);
        btndangky = findViewById(R.id.btndangky);
        btndangnhap = findViewById(R.id.btndangnhap);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mk1.getText().toString().equals("") || mk2.getText().toString().equals("") || sdt.getText().toString().equals("")) {
                    Toast.makeText(DangKy.this,"Chưa nhập thông tin đầy đủ",Toast.LENGTH_SHORT).show();
                }
                if(mk1.getText().toString().trim().equals(mk2.getText().toString().trim())){
                    dangkytk();

                }else{
                    Toast.makeText(DangKy.this,"Mật khẩu không giống nhau",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void dangkytk(){
        final String sodienthoai = sdt.getText().toString().trim();
        final String matkhau = mk1.getText().toString().trim();
        final RequestQueue requestQueue = Volley.newRequestQueue(DangKy.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.dangky, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    }else if (success.equals("0")){
                        Toast.makeText(DangKy.this,"Tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
        protected Map<String, String> getParams() {
            HashMap<String, String> params = new HashMap<>();
            params.put("sdt", sodienthoai);
            params.put("matkhau",matkhau);
            return params;
        }
        };
        requestQueue.add(stringRequest);
    }
}
