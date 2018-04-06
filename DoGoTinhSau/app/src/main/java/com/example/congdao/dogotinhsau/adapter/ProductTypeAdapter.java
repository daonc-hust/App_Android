package com.example.congdao.dogotinhsau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.model.ProductType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Cong Dao on 3/14/2018.
 */

public class ProductTypeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ProductType> listProductType;

    public ProductTypeAdapter(Context context, int layout, ArrayList<ProductType> listProductType) {
        this.context = context;
        this.layout = layout;
        this.listProductType = listProductType;
    }

    @Override
    public int getCount() {
        return listProductType.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        ImageView imgProductType;
        TextView txtProductType;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.imgProductType = (ImageView) view.findViewById(R.id.imageViewProductType);
            holder.txtProductType = (TextView) view.findViewById(R.id.textViewProductType);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ProductType productType = listProductType.get(i);

        Picasso.with(context).load(productType.getImage())
                .placeholder(R.drawable.no_image) // khi chua load dc anh tren mang thi load anh nay
                .error(R.drawable.error) // neu co loi xay ra khi load anh thi load anh nay
                .into(holder.imgProductType); // load thanh cong

        holder.txtProductType.setText(productType.getName());

        return view;
    }

}
