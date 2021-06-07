package com.example.orderfoodonline.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orderfoodonline.ListitemAdmin;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.Server.Server;
import com.example.orderfoodonline.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admindanhsachtaikhoan extends AppCompatActivity {
    ListView listviewdanhsachtaikhoan;
    public ArrayList<String> arrayListtaikhoan = new ArrayList<>();
    String sdt;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtaikhoan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admindanhsachtaikhoan.this, ListitemAdmin.class);
                startActivity(intent);
            }
        });
        listviewdanhsachtaikhoan = findViewById(R.id.listviewdanhsachtaikhoan);
        getdanhsachsanpham();
    }

    private void getdanhsachsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(Admindanhsachtaikhoan.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.danhsachtaikhoan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayListtaikhoan.add(String.valueOf(object.getInt("Sdt")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter	=	new ArrayAdapter<String>(Admindanhsachtaikhoan.this, android.R.layout.simple_list_item_1, arrayListtaikhoan);
                listviewdanhsachtaikhoan.setAdapter(adapter);
                registerForContextMenu(listviewdanhsachtaikhoan);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_taikhoan,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        int pos = info.position;
        sdt = arrayListtaikhoan.get(pos);
        switch (item.getItemId()) {

            case R.id.menuxoa:
                showAlertDialog();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Food Online Admin");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                xoataikhoan();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void xoataikhoan(){
        final RequestQueue requestQueue = Volley.newRequestQueue(Admindanhsachtaikhoan.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.xoataikhoan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(Admindanhsachtaikhoan.this,"xóa tài khoản thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Admindanhsachtaikhoan.this,Admindanhsachtaikhoan.class);
                        startActivity(intent);

                    }else  if(success.equals("0")){
                        Toast.makeText(Admindanhsachtaikhoan.this,"xóa  Thất bại ",Toast.LENGTH_SHORT).show();

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
                params.put("sdt",sdt);
                Log.e("TAG",sdt );
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
