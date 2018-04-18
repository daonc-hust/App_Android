package com.example.congdao.dogotinhsau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.adapter.BasketAdapter;
import com.example.congdao.dogotinhsau.model.Basket;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    Toolbar toolbarOrder;
    ListView lvGioHang;
    Button btnThanhToan, btnTiepTuc;
    TextView txtGioHang, txtTienThanhToan;

    ArrayList<Basket> arrayBasket;
    BasketAdapter basketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Mapping();
        ActionBar();
        CheckData();
        EvenUltil();

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderActivity.this, MainActivity.class));
            }
        });
    }

    private void EvenUltil() {
        long tongTien = 0;
        for (int i = 0; i < MainActivity.basketArrayList.size(); i++) {
            tongTien += MainActivity.basketArrayList.get(i).getPrice();
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTienThanhToan.setText(decimalFormat.format(tongTien) + "Đ");
    }

    private void CheckData() {
        if (MainActivity.basketArrayList.size() <= 0) {
            basketAdapter.notifyDataSetChanged();
            txtGioHang.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        } else {
            basketAdapter.notifyDataSetChanged();
            txtGioHang.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionBar() {
        setSupportActionBar(toolbarOrder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarOrder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Mapping() {
        toolbarOrder = (Toolbar) findViewById(R.id.toolbarOrder);
        btnThanhToan = (Button) findViewById(R.id.buttonThanhToan);
        btnTiepTuc = (Button) findViewById(R.id.buttonTiepTuc);
        lvGioHang = (ListView) findViewById(R.id.listViewGioHang);
        txtGioHang = (TextView) findViewById(R.id.textViewGioHang);
        txtTienThanhToan = (TextView) findViewById(R.id.textViewTienThanhToan);
        basketAdapter = new BasketAdapter(this, R.layout.raw_basket, MainActivity.basketArrayList);
        lvGioHang.setAdapter(basketAdapter);
    }
}
