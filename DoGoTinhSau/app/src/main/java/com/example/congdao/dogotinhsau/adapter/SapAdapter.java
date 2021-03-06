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
import java.util.List;

/**
 * Created by Cong Dao on 3/19/2018.
 */

public class SapAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Product> listSap;

    public SapAdapter(Context context, int layout, List<Product> listSap) {
        this.context = context;
        this.layout = layout;
        this.listSap = listSap;
    }

    @Override
    public int getCount() {
        return listSap.size();
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
        ImageView imgSap;
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
            holder.imgSap = (ImageView) view.findViewById(R.id.imgBanGhe);

            view.setTag(holder);
        } else {
            holder = (SapAdapter.ViewHolder) view.getTag();
        }

        Product doTho = listSap.get(i);

        holder.txtName.setText(doTho.getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###"); // 1,000,000 Đ
        holder.txtPrice.setText("Giá : " + decimalFormat.format(doTho.getPrice()) + " Đ");

        holder.txtDescribe.setMaxLines(2);
        holder.txtDescribe.setEllipsize(TextUtils.TruncateAt.END); // dài quá thì hiện ...
        holder.txtDescribe.setText(doTho.getDescribe());

        Picasso.with(context).load(doTho.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(holder.imgSap);

        return view;
    }
}
