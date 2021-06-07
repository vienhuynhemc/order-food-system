package com.example.orderfoodonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.donhangcuakhachhang;


import java.util.ArrayList;

public class Adapterdonhangcuakhachhang extends BaseAdapter {
    Context context;
    ArrayList<donhangcuakhachhang> arrayListDonhang;
    int layout;
    public Adapterdonhangcuakhachhang(Context context, int layout, ArrayList<donhangcuakhachhang> arrayListDonhang) {
        this.context = context;
        this.layout = layout;
        this.arrayListDonhang = arrayListDonhang;
    }

    @Override
    public int getCount() {
        return arrayListDonhang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListDonhang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(layout,null);
        TextView Tensp = convertView.findViewById(R.id.Tensp);
        TextView giasp = convertView.findViewById(R.id.giasp);
        TextView soluong = convertView.findViewById(R.id.soluong);
        Tensp.setText(arrayListDonhang.get(position).getTensp());
        giasp.setText(arrayListDonhang.get(position).getGiatien()+"");
        soluong.setText(arrayListDonhang.get(position).getSoluong()+"");


        return convertView;
    }
}
