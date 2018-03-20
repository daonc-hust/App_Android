package com.example.congdao.sambaby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cong Dao on 3/5/2018.
 */

public class AdapterMusic extends BaseAdapter {
    private int layout;
    private Context context;
    private List<Music> listMusic;

    public AdapterMusic(int layout, Context context, List<Music> listMusic) {
        this.layout = layout;
        this.context = context;
        this.listMusic = listMusic;
    }

    @Override
    public int getCount() {
        return listMusic.size();
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
        public TextView txtName, txtSinger;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) view.findViewById(R.id.textViewName);
            holder.txtSinger = (TextView) view.findViewById(R.id.textViewSinger);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Music music = listMusic.get(i);
        holder.txtName.setText(music.getName());
        holder.txtSinger.setText(music.getSinger());
        return view;
    }
}
