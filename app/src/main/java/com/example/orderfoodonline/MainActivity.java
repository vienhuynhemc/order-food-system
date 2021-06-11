package com.example.orderfoodonline;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodonline.adapter.AdapterLoaisp;
import com.example.orderfoodonline.model.Loaisp;
import com.example.orderfoodonline.model.gioHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Loaisp> mangloaisp;
    public static ArrayList<gioHang> mangGioHang;
    AdapterLoaisp loaispAdapter;
    Animation in, out;
    ViewFlipper viewFlipper;
    RecyclerView RecyclerViewLoaisp;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        addEventViewFlipper();
        getdulieuloaisp();
    }


    private void getdulieuloaisp() {
        // Của người ta
//        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaisp, response -> {
//            for (int i = 0; i < response.length(); i++) {
//                try {
//                    JSONObject object = response.getJSONObject(i);
//                    id = object.getInt("id");
//                    tenloaisp = object.getString("Tenloai");
//                    hinhanhloaisp = object.getString("Imageloaisp");
//                    mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
//                    loaispAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            mangloaisp.add(new Loaisp(-1, "Trang Chủ", "https://vietadsgroup.vn/Uploads/files/trangchu-la-gi.png"));
//            mangloaisp.add(new Loaisp(-2, "Liên Hệ", "http://capnuocbenthanh.com/images/dtlienhe_1.jpg"));
//            mangloaisp.add(new Loaisp(-3, "Thông Tin", "http://kinhtevadubao.vn/uploads/images/news/1515687283_news_10383.jpg"));
//            mangloaisp.add(new Loaisp(-4, "Giỏ Hàng", "https://img.icons8.com/nolan/2x/add-shopping-cart.png"));
//            loaispAdapter.notifyDataSetChanged();
//        }, error -> {
//        });
//        requestQueue.add(jsonArrayRequest);
        // Của tui real time
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference root = firebaseDatabase.getReference();
        DatabaseReference trang_chu = root.child("trang_chu");
        trang_chu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mangloaisp.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    mangloaisp.add(new Loaisp(Integer.parseInt(d.getKey()),
                            d.child("ten_loai").getValue().toString(),
                            d.child("hinh_anh").getValue().toString()));
                    loaispAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void anhXa() {
        in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        viewFlipper = findViewById(R.id.viewflipper);
        RecyclerViewLoaisp = findViewById(R.id.RecyclerViewLoaisp);
        mangloaisp = new ArrayList<Loaisp>();
        loaispAdapter = new AdapterLoaisp(MainActivity.this, mangloaisp);
        RecyclerViewLoaisp.setHasFixedSize(true);
        RecyclerViewLoaisp.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        RecyclerViewLoaisp.setAdapter(loaispAdapter);
        if (mangGioHang != null) {

        } else {
            mangGioHang = new ArrayList<com.example.orderfoodonline.model.gioHang>();
        }
    }

    private void addEventViewFlipper() {
        ArrayList<String> QC = new ArrayList<>();
        QC.add("https://chuphinhmonan.com/wp-content/uploads/2017/03/avalon-1.jpg");
        QC.add("http://www.chupsanpham.vn/uploads/2/1/6/8/21683184/3750050_orig.png");
        QC.add("http://channel.mediacdn.vn//prupload/164/2014/08/img20140822013035216.jpg");
        QC.add("https://anybuy.vn/tin-tuc/wp-content/uploads/2014/04/banner-quang-cao-bap-rang-do-uong-tai-galaxy.jpg");
        for (int i = 0; i < QC.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.get().load(QC.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);
    }
}
