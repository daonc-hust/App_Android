package com.example.congdao.dogotinhsau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.congdao.dogotinhsau.R;

public class OrderActivity extends AppCompatActivity {

    Toolbar toolbarOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Mapping();
        ActionBar();
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
    }
}
