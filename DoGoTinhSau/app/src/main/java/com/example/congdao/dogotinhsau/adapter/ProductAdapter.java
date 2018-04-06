package com.example.congdao.dogotinhsau.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.activity.BanGheActivity;
import com.example.congdao.dogotinhsau.activity.KeTVActivity;
import com.example.congdao.dogotinhsau.activity.ProductDetails;
import com.example.congdao.dogotinhsau.model.Product;
import com.example.congdao.dogotinhsau.ulti.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Cong Dao on 3/18/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    private Context context;
    private int layout;
    private ArrayList<Product> arrayProduct;

    public ProductAdapter(Context context, int layout, ArrayList<Product> arrayProduct) {
        this.context = context;
        this.layout = layout;
        this.arrayProduct = arrayProduct;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_new_product, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        Product product = arrayProduct.get(position);

        holder.txtName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText("Giá : " + decimalFormat.format(product.getPrice()) + " Đ");

        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(holder.imgProduct);

        holder.setItemClickListenner(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, "Long CLick", Toast.LENGTH_SHORT).show();
                } else {
                    view.getContext().startActivity(new Intent(context, BanGheActivity.class));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayProduct.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ImageView imgProduct;
        public TextView txtName, txtPrice;
        private ItemClickListener itemClickListener;

        public void setItemClickListenner(ItemClickListener itemClickListenner) {
            this.itemClickListener = itemClickListenner;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return false;
        }

        public ItemHolder(View itemView) {
            super(itemView);
            imgProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            txtName = (TextView) itemView.findViewById(R.id.txtNameProduct);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }
}
