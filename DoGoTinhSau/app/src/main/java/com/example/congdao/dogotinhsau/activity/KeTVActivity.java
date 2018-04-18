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
import com.example.congdao.dogotinhsau.adapter.KeTVAdapter;
import com.example.congdao.dogotinhsau.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KeTVActivity extends AppCompatActivity {

    Toolbar toolbarKeTV;
    ListView lvKeTV;

    ArrayList<Product> arrayKeTV;
    KeTVAdapter keTVAdapter;

    private DatabaseReference databaseKeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_tv);
        Mapping();
        ActionToolbar();

        databaseKeTV = FirebaseDatabase.getInstance().getReference();
        GetDataKeTV();
        lvKeTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KeTVActivity.this,ProductDetails.class);
                intent.putExtra("ThongTinSanPham",arrayKeTV.get(i));
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
        setSupportActionBar(toolbarKeTV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarKeTV.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetDataKeTV() {
        databaseKeTV.child("SanPham").child("KeTV").child("KeCoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeCotHuongVan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeCotNho").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeGuLao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeGuTa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeHuongVan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeLechHuongDa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseKeTV.child("SanPham").child("KeTV").child("KeMo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String describe = dataSnapshot.child("describe").getValue().toString();
                int id = dataSnapshot.child("id").getValue(int.class);
                String image = dataSnapshot.child("image").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Integer price = dataSnapshot.child("price").getValue(Integer.class);

                arrayKeTV.add(new Product(describe, id, image, name, price));
                keTVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void Mapping() {
        toolbarKeTV = (Toolbar) findViewById(R.id.toolbarKeTV);
        lvKeTV = (ListView) findViewById(R.id.listViewKeTV);

        arrayKeTV = new ArrayList<>();
        keTVAdapter = new KeTVAdapter(getApplicationContext(), R.layout.raw_product, arrayKeTV);
        lvKeTV.setAdapter(keTVAdapter);
    }
}
