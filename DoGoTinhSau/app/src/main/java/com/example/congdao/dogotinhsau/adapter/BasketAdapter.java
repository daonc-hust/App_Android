package com.example.congdao.dogotinhsau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.activity.MainActivity;
import com.example.congdao.dogotinhsau.model.Basket;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class BasketAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Basket> basketList;

    public BasketAdapter(Context context, int layout, List<Basket> basketList) {
        this.context = context;
        this.layout = layout;
        this.basketList = basketList;
    }

    @Override
    public int getCount() {
        return basketList.size();
    }

    @Override
    public Object getItem(int i) {
        return basketList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        TextView txtName, txtPrice;
        ImageView imgProduct;
        Button btnMinus, btnValues, btnPlus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.imgProduct = (ImageView) view.findViewById(R.id.imageProduct);
            holder.txtName = (TextView) view.findViewById(R.id.textName);
            holder.txtPrice = (TextView) view.findViewById(R.id.textPrice);
            holder.btnMinus = (Button) view.findViewById(R.id.buttonMinus);
            holder.btnValues = (Button) view.findViewById(R.id.buttonValues);
            holder.btnPlus = (Button) view.findViewById(R.id.buttonPlus);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Basket basket = basketList.get(i);
        holder.txtName.setText(basket.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(basket.getPrice()) + "Đ");
        Picasso.with(context).load(basket.getImage())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(holder.imgProduct);
        holder.btnValues.setText(basket.getNumber() + "");

        int number = Integer.parseInt(holder.btnValues.getText().toString());
        if (number > 10) {
            holder.btnPlus.setVisibility(View.INVISIBLE);
            holder.btnMinus.setVisibility(View.VISIBLE);
        } else if (number <= 1) {
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinus.setVisibility(View.INVISIBLE);
        } else if (number >= 1) {
            holder.btnPlus.setVisibility(View.VISIBLE);
            holder.btnMinus.setVisibility(View.VISIBLE);
        }

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNumber = Integer.parseInt(holder.btnValues.getText().toString()) + 1;
                int presentNumber = MainActivity.basketArrayList.get(i).getNumber();
                long presentPrice = MainActivity.basketArrayList.get(i).getPrice();

                MainActivity.basketArrayList.get(i).setNumber(newNumber);
                long newPrice = (presentPrice * newNumber) / presentNumber;
                MainActivity.basketArrayList.get(i).setPrice((int) newPrice);
            }
        });

        return view;
    }
}
