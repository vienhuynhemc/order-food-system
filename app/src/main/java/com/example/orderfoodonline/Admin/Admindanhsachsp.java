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
import com.example.orderfoodonline.adapter.DanhsachspAdapter;
import com.example.orderfoodonline.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admindanhsachsp extends AppCompatActivity {
    int idsp;
    ListView Listdanhsachsanpham;
    ArrayList<SanPham>arrayListdanhsachsp;
    DanhsachspAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindanhsachsp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admindanhsachsp.this, ListitemAdmin.class);
                startActivity(intent);

            }
        });

        Listdanhsachsanpham = findViewById(R.id.Listdanhsachsanpham);
        arrayListdanhsachsp=new ArrayList<>();
        sanPhamAdapter= new DanhsachspAdapter(Admindanhsachsp.this,R.layout.row_foodsp,arrayListdanhsachsp);
        Listdanhsachsanpham.setAdapter(sanPhamAdapter);
        getdanhsachsanpham();
        registerForContextMenu(Listdanhsachsanpham);
    }


    private void getdanhsachsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue(Admindanhsachsp.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.danhsachsanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayListdanhsachsp.add(new SanPham(object.getInt("idsp"),object.getString("tensp"),object.getInt("gia"),object.getString("hinhanh"),object.getString("mota"),object.getInt("loaisp")));
                        sanPhamAdapter.notifyDataSetChanged();
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        int pos = info.position;
        SanPham delItem = arrayListdanhsachsp.get(pos);
        idsp = arrayListdanhsachsp.get(pos).getId();
        String tensanpham = arrayListdanhsachsp.get(pos).getTensanpham();
        int giasanpham = arrayListdanhsachsp.get(pos).getGiasanpham();
        String mota = arrayListdanhsachsp.get(pos).getMotasanpham();
        String hinhanh = arrayListdanhsachsp.get(pos).getHinhanhsanpham();

        switch (item.getItemId()) {
            case R.id.menuchinhsua:
               Intent intent = new Intent(Admindanhsachsp.this,Adminchinhsua.class);
                intent.putExtra("idsp",idsp);
                intent.putExtra("tensanpham",tensanpham);
                intent.putExtra("giasanpham",giasanpham);
                intent.putExtra("mota",mota);
                intent.putExtra("hinhanh",hinhanh);

                startActivity(intent);

                break;
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
                xoasanpham();
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

    private void xoasanpham(){
        final RequestQueue requestQueue = Volley.newRequestQueue(Admindanhsachsp.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.xoasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1")){
                        Toast.makeText(Admindanhsachsp.this,"xóa sản phẩm thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Admindanhsachsp.this,Admindanhsachsp.class);
                        startActivity(intent);

                    }else  if(success.equals("0")){
                        Toast.makeText(Admindanhsachsp.this,"xóa  Thất bại ",Toast.LENGTH_SHORT).show();

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
                Log.e("TAG", ""+idsp );
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
