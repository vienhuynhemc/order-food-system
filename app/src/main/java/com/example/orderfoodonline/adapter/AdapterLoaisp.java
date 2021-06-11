package com.example.orderfoodonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodonline.FoodSanPham;
import com.example.orderfoodonline.GioHang;
import com.example.orderfoodonline.Lienhe;
import com.example.orderfoodonline.MainActivity;
import com.example.orderfoodonline.R;
import com.example.orderfoodonline.Thongtintaikhoan;
import com.example.orderfoodonline.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLoaisp extends RecyclerView.Adapter<AdapterLoaisp.itemHolder> {
    Context context;
    ArrayList<Loaisp> arrayListLoaisp;
    private static AdapterView.OnItemClickListener listener;

    public AdapterLoaisp(Context context, ArrayList<Loaisp> arrayListLoaisp) {
        this.context = context;
        this.arrayListLoaisp = arrayListLoaisp;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loaisp, null);
        itemHolder itemHolder = new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        Loaisp loaisp = arrayListLoaisp.get(position);
        holder.Tenloaisp.setText(loaisp.getTenLoaiSanPham());
        Picasso.get().load(loaisp.getHinhAnhLoaiSanPham()).centerCrop().resize(150, 150).into(holder.Imageloaisp);
    }

    @Override
    public int getItemCount() {
        return arrayListLoaisp.size();
    }


    public class itemHolder extends RecyclerView.ViewHolder {
        public ImageView Imageloaisp;
        public TextView Tenloaisp;

        public itemHolder(View itemView) {
            super(itemView);
            Imageloaisp = itemView.findViewById(R.id.img_loaisp);
            Tenloaisp = itemView.findViewById(R.id.tv_loaisp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = arrayListLoaisp.get(getPosition()).getId();
                    switch (i) {
                        case 1: {
                            Intent intent = new Intent(context, FoodSanPham.class);
                            intent.putExtra("idloaisp", i);
                            context.startActivity(intent);
                        }
                        break;
// ?? Không hiểu người ta viết cái gì ở đây
//                        case 2: {
//                            Intent intent = new Intent(context, FoodSanPham.class);
//                            intent.putExtra("idloaisp", i);
//                            context.startActivity(intent);
//                             }
//                          break;
//
//                        case 3: {
//                            Intent intent = new Intent(context, FoodSanPham.class);
//                            intent.putExtra("idloaisp", i);
//                            context.startActivity(intent);
//                        }
//                            break;
//
//                        case 4: {
//                            Intent intent = new Intent(context, FoodSanPham.class);
//                            intent.putExtra("idloaisp", i);
//                            context.startActivity(intent);
//                        }
//                        break;


                        case -1: {
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                        break;

                        case -2: {
                            Intent intent = new Intent(context, Lienhe.class);
                            context.startActivity(intent);
                        }
                        break;

                        case -3: {
                            Intent intent = new Intent(context, Thongtintaikhoan.class);
                            context.startActivity(intent);
                        }
                        break;

                        case -4: {
                            Intent intent = new Intent(context, GioHang.class);
                            context.startActivity(intent);
                        }
                        break;
                    }
                }
            });
        }
    }
}

