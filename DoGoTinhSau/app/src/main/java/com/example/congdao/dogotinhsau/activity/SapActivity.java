package com.example.congdao.dogotinhsau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.adapter.SapAdapter;
import com.example.congdao.dogotinhsau.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SapActivity extends AppCompatActivity {

    Toolbar toolbarSap;
    ListView lvSap;

    ArrayList<Product> arraySap;
    SapAdapter sapAdapter;

    private DatabaseReference databaseSap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sap);
        Mapping();
        ActionToolbar();
        databaseSap = FirebaseDatabase.getInstance().getReference();

        GetDataSap();

        lvSap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SapActivity.this, ProductDetails.class);
                intent.putExtra("ThongTinSanPham", arraySap.get(i));
                startActivity(intent);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarSap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSap.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetDataSap() {
        databaseSap.child("SanPham").child("Sap").child("SapGu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arraySap.add(new Product(describe, id, image, name, price));
                sapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseSap.child("SanPham").child("Sap").child("SapGuTa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arraySap.add(new Product(describe, id, image, name, price));
                sapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void Mapping() {
        toolbarSap = (Toolbar) findViewById(R.id.toolbarSap);
        lvSap = (ListView) findViewById(R.id.listViewSap);

        arraySap = new ArrayList<>();
        sapAdapter = new SapAdapter(getApplicationContext(), R.layout.raw_product, arraySap);
        lvSap.setAdapter(sapAdapter);
    }
}
