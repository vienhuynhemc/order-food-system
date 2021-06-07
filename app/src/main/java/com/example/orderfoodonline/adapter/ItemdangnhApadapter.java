package com.example.orderfoodonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.Itemdangnhap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemdangnhApadapter extends ArrayAdapter<Itemdangnhap> {
    Context context;
    ArrayList<Itemdangnhap> arrayList;
    int layout;


    public ItemdangnhApadapter (Context context, int resource, ArrayList<Itemdangnhap> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layout =resource;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(layout, null);
        TextView tvTenloaisp = view.findViewById(R.id.textView);
        ImageView imgloaisp = view.findViewById(R.id.imageView);
        tvTenloaisp.setText(arrayList.get(i).getTen());
        Picasso.get().load(arrayList.get(i).getHinh()).centerCrop().resize(150, 150).into(imgloaisp);
        return view;
    }
}
