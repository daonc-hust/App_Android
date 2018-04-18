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
import com.example.congdao.dogotinhsau.adapter.DoThoAdapter;
import com.example.congdao.dogotinhsau.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoThoActivity extends AppCompatActivity {

    Toolbar toolbarDoTho;
    ListView lvDoTho;
    DoThoAdapter doThoAdapter;
    ArrayList<Product> arrayDoTho;

    DatabaseReference databaseDoTho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_tho);
        Mapping();
        ActionToolbar();

        databaseDoTho = FirebaseDatabase.getInstance().getReference();
        GetDataDoTho();

        lvDoTho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoThoActivity.this, ProductDetails.class);
                intent.putExtra("ThongTinSanPham", arrayDoTho.get(i));
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
        setSupportActionBar(toolbarDoTho);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDoTho.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetDataDoTho() {
        databaseDoTho.child("SanPham").child("DoTho").child("AnGian").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayDoTho.add(new Product(describe, id, image, name, price));
                doThoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void Mapping() {
        toolbarDoTho = (Toolbar) findViewById(R.id.toolbarDoTho);
        lvDoTho = (ListView) findViewById(R.id.listViewDoTho);

        arrayDoTho = new ArrayList<>();
        doThoAdapter = new DoThoAdapter(getApplicationContext(), R.layout.raw_product, arrayDoTho);
        lvDoTho.setAdapter(doThoAdapter);
    }
}
