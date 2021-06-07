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
import android.widget.Toast;

import com.example.orderfoodonline.Donhancuasanpham;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.donhang;
import com.example.orderfoodonline.model.donhangcuakhachhang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DanhsachdonhangAdapter extends BaseAdapter {
    Context context;
    ArrayList<donhang> arrayListDonhang;
    int layout;
    public DanhsachdonhangAdapter(Context context, int layout, ArrayList<donhang> arrayListDonhang) {
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(layout,null);
        TextView ten = view.findViewById(R.id.tenkhdonhang);
        TextView sdt = view.findViewById(R.id.sdtdonhang);
        TextView diachi = view.findViewById(R.id.diachidonhang);
        Button xemchitiet = view.findViewById(R.id.buttonxem);
        ten.setText(arrayListDonhang.get(position).getTen());
        sdt.setText(arrayListDonhang.get(position).getSodienthoai()+"");
        diachi.setText(arrayListDonhang.get(position).getDiachi());

        xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Donhancuasanpham.class);
                intent.putExtra("madonhang",arrayListDonhang.get(position).getMadonhang());
                Toast.makeText(context,"madonhang"+arrayListDonhang.get(position).getMadonhang(),Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        return view;
    }
}
