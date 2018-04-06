package com.example.congdao.dogotinhsau.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Cong Dao on 3/18/2018.
 */

public class BanGheAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Product> listBanGhe;

    public BanGheAdapter(Context context, int layout, ArrayList<Product> arrayBanGhe) {
        this.context = context;
        this.layout = layout;
        this.listBanGhe = arrayBanGhe;
    }

    @Override
    public int getCount() {
        return listBanGhe.size();
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
        TextView txtName, txtPrice, txtDescribe;
        ImageView imgBanGhe;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            holder.txtName = (TextView) view.findViewById(R.id.textViewBanGhe);
            holder.txtPrice = (TextView) view.findViewById(R.id.textViewGiaBanGhe);
            holder.txtDescribe = (TextView) view.findViewById(R.id.textViewMotaBanGhe);
            holder.imgBanGhe = (ImageView) view.findViewById(R.id.imgBanGhe);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product banGhe = listBanGhe.get(i);

        holder.txtName.setText(banGhe.getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###"); // 1,000,000 Đ
        holder.txtPrice.setText("Giá : " + decimalFormat.format(banGhe.getPrice()) + " Đ");

        holder.txtDescribe.setMaxLines(2);
        holder.txtDescribe.setEllipsize(TextUtils.TruncateAt.END); // dài quá thì hiện ...
        holder.txtDescribe.setText(banGhe.getDescribe());

        Picasso.with(context).load(banGhe.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(holder.imgBanGhe);

        return view;
    }
}
