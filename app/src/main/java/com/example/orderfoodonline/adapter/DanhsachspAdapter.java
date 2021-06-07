package com.example.orderfoodonline.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DanhsachspAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham>arrayListSanpham;
    int layout;
    public DanhsachspAdapter(Context context, int layout, ArrayList<SanPham> arrayListSanpham) {
        this.context = context;
        this.layout = layout;
        this.arrayListSanpham = arrayListSanpham;
    }


    @Override
    public int getCount() {
        return arrayListSanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListSanpham.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(layout,null);
        ImageView imgHinhAnhSanpham = view.findViewById(R.id.imgsp);
        TextView tvTensanpham = view.findViewById(R.id.tv_tensp);
        TextView tvGiasanpham = view.findViewById(R.id.tv_gia);
        TextView tvMotasanpham = view.findViewById(R.id.tv_mota);
        tvTensanpham.setText(arrayListSanpham.get(position).getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvGiasanpham.setText("Giá :"+decimalFormat.format(arrayListSanpham.get(position).getGiasanpham())+" Đ");
        tvMotasanpham.setText(arrayListSanpham.get(position).getMotasanpham());
        Picasso.get().load(arrayListSanpham.get(position).getHinhanhsanpham()).centerCrop().resize(150,150).into(imgHinhAnhSanpham);
        return view;
    }

}
