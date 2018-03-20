package com.example.congdao.sambaby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cong Dao on 3/5/2018.
 */

public class AdapterImage extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ImageViewSam> imageViewList;

    public AdapterImage(Context context, int layout, List<ImageViewSam> imageViewList) {
        this.context = context;
        this.layout = layout;
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.imageViewSamBaby);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImageViewSam imageViewSam = imageViewList.get(i);
        holder.imageView.setImageResource(imageViewSam.getImage());

        return view;
    }
}
