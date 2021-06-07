package com.example.orderfoodonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderfoodonline.ChiTietSanpham;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.itemHolder>{
    Context context;
    ArrayList<SanPham>arrayListSanpham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListSanpham) {
        this.context = context;
        this.arrayListSanpham = arrayListSanpham;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_foodsp,null);
        itemHolder itemHolder=new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        SanPham sanPham= new SanPham();
        sanPham=arrayListSanpham.get(position);
        holder.tvTensanpham.setText(sanPham.getTensanpham());
        holder.tvMotasanpham.setText(sanPham.getMotasanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.tvGiasanpham.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" Đ");
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(150,150).into(holder.imgHinhAnhSanpham);
        holder.tvTimestart.setText(sanPham.getTimestart()+"");
        holder.tvTimeend.setText(sanPham.getTimeend()+"");



    }

    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhAnhSanpham;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;
        public TextView tvMotasanpham;
        public TextView tvTimestart;
        public TextView tvTimeend;


        public itemHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham=itemView.findViewById(R.id.imgsp);
            tvTensanpham=itemView.findViewById(R.id.tv_tensp);
            tvGiasanpham=itemView.findViewById(R.id.tv_gia);
            tvMotasanpham=itemView.findViewById(R.id.tv_mota);
            tvTimestart=itemView.findViewById(R.id.timestart);
            tvTimeend=itemView.findViewById(R.id.Timeend);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, ChiTietSanpham.class);
                    intent.putExtra("tensp",arrayListSanpham.get(getPosition()).getTensanpham());
                    intent.putExtra("gia",arrayListSanpham.get(getPosition()).getGiasanpham());
                    intent.putExtra("hinhanh",arrayListSanpham.get(getPosition()).getHinhanhsanpham());
                    intent.putExtra("mota",arrayListSanpham.get(getPosition()).getMotasanpham());
                    intent.putExtra("id",arrayListSanpham.get(getPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
