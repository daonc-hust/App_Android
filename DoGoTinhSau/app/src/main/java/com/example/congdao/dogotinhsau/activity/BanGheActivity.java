package com.example.congdao.dogotinhsau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.adapter.BanGheAdapter;
import com.example.congdao.dogotinhsau.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BanGheActivity extends AppCompatActivity {

    Toolbar toolbarBanGhe;
    ListView lvBanGhe;
    BanGheAdapter banGheAdapter;
    ArrayList<Product> arrayBanGhe;

    public DatabaseReference databaseBanGhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_ghe);
        Mapping();
        ActionToolbar();

        databaseBanGhe = FirebaseDatabase.getInstance().getReference();
        GetDataBanGhe();

        lvBanGhe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BanGheActivity.this, ProductDetails.class);
                intent.putExtra("ThongTinSanPham", arrayBanGhe.get(i));
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuBasket:
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarBanGhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarBanGhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetDataBanGhe() {
        databaseBanGhe.child("SanPham").child("BanGhe").child("GuTaTay10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("GuTaTay12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("HuongDaTay10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("HopHuongLao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("HuongVanTay10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("HuongXamTay12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseBanGhe.child("SanPham").child("BanGhe").child("MinhQuocTay10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayBanGhe.add(new Product(describe, id, image, name, price));
                banGheAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void Mapping() {
        toolbarBanGhe = (Toolbar) findViewById(R.id.toobarBanGhe);
        lvBanGhe = (ListView) findViewById(R.id.listViewBanGhe);

        arrayBanGhe = new ArrayList<>();
        banGheAdapter = new BanGheAdapter(getApplicationContext(), R.layout.raw_product, arrayBanGhe);
        lvBanGhe.setAdapter(banGheAdapter);
//        arrayBanGhe.add(new Product("Chất lượng ok",
//                1, "http://soncuago.net/wp-content/uploads/2016/04/1684-bo-ban-ghe-go-trien-tay-10-6-mon-go-xoan-dao-sl2341.jpg",
//                "Bộ bàn ghế gụ ta tay 10", 30000000));
    }
}
