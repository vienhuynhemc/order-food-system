package com.example.orderfoodonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.gioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.orderfoodonline.GioHang.gioHangAdapter;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<gioHang>arryGioHang;
    public GioHangAdapter(Context context, ArrayList<gioHang> arryGioHang) {
        this.context = context;
        this.arryGioHang = arryGioHang;
    }

    @Override
    public int getCount() {
        return arryGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
        public ImageView imggiohang;
        public Button btngiatri,btnxoa;
        public TextView tengiohang,giagiohang;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.row_giohangsanpham,null);
        tengiohang=convertView.findViewById(R.id.tengiohang);
        giagiohang=convertView.findViewById(R.id.giagiohang);
        imggiohang=convertView.findViewById(R.id.imggiohang);
        btngiatri=convertView.findViewById(R.id.btngiatri);
        btnxoa=convertView.findViewById(R.id.btnxoa);

        tengiohang.setText(arryGioHang.get(position).getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        giagiohang.setText(decimalFormat.format(arryGioHang.get(position).getGiasp())+" Đ");

        Picasso.get().load(arryGioHang.get(position).getHinhsp()).centerCrop().resize(150,150).into(imggiohang);
        btngiatri.setText(arryGioHang.get(position).getSoluongsp()+"");

        //xóa sản phẩm
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arryGioHang.remove(position);
                gioHangAdapter.notifyDataSetChanged();
                Intent intent = new Intent(context, com.example.orderfoodonline.GioHang.class);
                context.startActivity(intent);

            }
        });

             return convertView;
    }
}
