package com.example.orderfoodonline;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
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

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class Thongtintaikhoan extends AppCompatActivity {
    public  static int Dangnhapthanhcong =0;
    Button btndangky,btndangnhap;
    EditText sdt,mkk1;
    String sodienthoai,matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtintaikhoan);
        if(getIntent().getIntExtra("Dangnhapthanhcong",-1) != -1){
            Dangnhapthanhcong =0;

        }
            if(Dangnhapthanhcong==1){
                Intent intent = new Intent(this, Tkitemdangnhap.class);
                startActivity(intent);
            }else if(Dangnhapthanhcong==2){
                Intent intent = new Intent(this, ListitemAdmin.class);
                startActivity(intent);
            }

            Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtintaikhoan.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btndangky = findViewById(R.id.btndangky);
        btndangnhap = findViewById(R.id.btndangnhap);
        sdt = findViewById(R.id.sdtdonhang);
        mkk1 = findViewById(R.id.mkk1);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtintaikhoan.this,DangKy.class);
                startActivity(intent);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sodienthoai = sdt.getText().toString().trim();
                 matkhau = mkk1.getText().toString().trim();
                if(sodienthoai.isEmpty() || matkhau.isEmpty()){
                    Toast.makeText(Thongtintaikhoan.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    LoginTaiKhoan();
                }
            }
        });


    }

    private void LoginTaiKhoan(){
        final RequestQueue requestQueue = Volley.newRequestQueue(Thongtintaikhoan.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.dangnhap, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")) {
                        Toast.makeText(Thongtintaikhoan.this, "Đăng Nhập  thành công", Toast.LENGTH_SHORT).show();
                        Dangnhapthanhcong = 1;
                        Intent intent = new Intent(Thongtintaikhoan.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else if(success.equals("2")){
                        Toast.makeText(Thongtintaikhoan.this,"Đăng Nhập  thành công",Toast.LENGTH_SHORT).show();
                        Dangnhapthanhcong = 2;
                        Intent intent1 = new Intent(Thongtintaikhoan.this,MainActivity.class);
                        startActivity(intent1);
                    }else if (success.equals("0")){
                        Toast.makeText(Thongtintaikhoan.this,"Tài khoản Và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
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
                params.put("sdt", sodienthoai);
                params.put("matkhau",matkhau);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}
