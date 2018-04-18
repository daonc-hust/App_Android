package com.example.congdao.dogotinhsau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.adapter.ProductAdapter;
import com.example.congdao.dogotinhsau.adapter.ProductTypeAdapter;
import com.example.congdao.dogotinhsau.model.Basket;
import com.example.congdao.dogotinhsau.model.Product;
import com.example.congdao.dogotinhsau.model.ProductType;
import com.example.congdao.dogotinhsau.ulti.CheckConnection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtsp;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    RecyclerView rvMainScreen;
    ListView lvMainScreen;
    DrawerLayout drawerLayout;

    ArrayList<ProductType> typeArrayList;
    ArrayList<Product> arrayProduct;
    ProductAdapter adapterProduct;
    ProductTypeAdapter adapterProductType;

    public static ArrayList<Basket> basketArrayList;

    public DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();

        database = FirebaseDatabase.getInstance().getReference();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetProductType();
            GetNewProduct();
            CatchOnItemListView();
        } else {
            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            ActionBar();
            ActionViewFlipper();
        }
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

    private void CatchOnItemListView() {
        lvMainScreen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            startActivity(new Intent(MainActivity.this, InformationActivity.class));
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            startActivity(new Intent(MainActivity.this, BanGheActivity.class));
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            startActivity(new Intent(MainActivity.this, DoThoActivity.class));
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            startActivity(new Intent(MainActivity.this, SapActivity.class));
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            startActivity(new Intent(MainActivity.this, KeTVActivity.class));
                        } else {
                            CheckConnection.showToast(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
            }
        });
    }

    private void GetProductType() {
        database.child("LoaiSanPham").child("KeTivi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                typeArrayList.add(new ProductType(dataSnapshot.getValue().toString(), "Kệ TV"));
                adapterProductType.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.child("LoaiSanPham").child("Sap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                typeArrayList.add(new ProductType(dataSnapshot.getValue().toString(), "Sập"));
                adapterProductType.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void GetNewProduct() {
        database.child("SanPham").child("BanGhe").child("GuTaTay12").child("price").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayProduct.add(new Product("Chất lượng ok", 2, "https://dogotinhsau.com/wp-content/uploads/2018/01/IMG_20171230_122503-450x600.jpg",
                        "Bộ bàn ghế gụ ta tay 12", dataSnapshot.getValue(Integer.class)));
                adapterProduct.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        database.child("SanPham").child("DoTho").child("AnGian").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                arrayProduct.add(new Product("ab", 3, "https://dogotinhsau.com/wp-content/uploads/2018/01/an-gian-350x450.jpg",
//                        "Án Gian Gỗ Mít", dataSnapshot.getValue(Integer.class)));
//                adapterProduct.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        database.child("SanPham").child("GiuongNgu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayProduct.add(new Product("asdas", 4, "https://dogotinhsau.com/wp-content/uploads/2018/01/IMG_20171230_123110-350x450.jpg",
                        "Giường ngủ gỗ gụ lào", dataSnapshot.getValue(Integer.class)));
                adapterProduct.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void ActionViewFlipper() {
        ArrayList<String> arrayImage = new ArrayList<>();
        arrayImage.add("https://dogotinhsau.com/wp-content/uploads/2018/01/ban-ghe-1200x800.jpg");
        arrayImage.add("https://dogotinhsau.com/wp-content/uploads/2017/12/bedroom-1940169-1200x800-1200x800.jpg");
        arrayImage.add("https://dogotinhsau.com/wp-content/uploads/2018/01/banner-tu-bep-nha-chung-cu-2-1400x566.png");

        for (int i = 0; i < arrayImage.size(); i++) {
            ImageView imageView = new ImageView(this);
            Picasso.with(getApplicationContext()).load(arrayImage.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setAutoStart(true);
        Animation animLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_left);
        Animation animRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_to_right);
        viewFlipper.setInAnimation(animLeft);
        viewFlipper.setOutAnimation(animRight);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Mapping() {
        txtsp = (TextView) findViewById(R.id.txtsp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        rvMainScreen = (RecyclerView) findViewById(R.id.recyclerViewMainScreen);
        lvMainScreen = (ListView) findViewById(R.id.listViewMainScreen);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        if (basketArrayList != null) {

        } else {
            basketArrayList = new ArrayList<>();
        }

        typeArrayList = new ArrayList<>();
        adapterProductType = new ProductTypeAdapter(this, R.layout.raw_product_type, typeArrayList);
        lvMainScreen.setAdapter(adapterProductType);
        //typeArrayList.add(new ProductType("https://pbs.twimg.com/profile_images/578114316634714112/moAKi404_400x400.png", "Trang chính"));
        typeArrayList.add(new ProductType("http://help.joescan.com/download/attachments/360466/081552-glossy-waxed-wood-icon-business-home5.png?api=v2", "Trang chính"));
        typeArrayList.add(new ProductType("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/glossy-waxed-wood-icons-business/081614-glossy-waxed-wood-icon-business-phone-solid.png", "Thông tin"));
        typeArrayList.add(new ProductType("http://soncuago.net/wp-content/uploads/2016/04/1684-bo-ban-ghe-go-trien-tay-10-6-mon-go-xoan-dao-sl2341.jpg", "Bàn ghế"));
        typeArrayList.add(new ProductType("http://dogoyenlac.com/wp-content/uploads/2015/11/tu-tho-go-dieu-khac-01.png", "Tủ thờ"));
        //typeArrayList.add(new ProductType("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStAJ-vfASBEQxrBRIWmk-xY1O-pGbitNsa3Gzv4S8TV7q22OPs", "Liên hệ"));
        //typeArrayList.add(new ProductType("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5kw5TQO8Lm08Riqc2fNepnx9Yb5U-_mT3OOVU6tx3tBdGlL9j", "Thông tin"));

        arrayProduct = new ArrayList<>();
        adapterProduct = new ProductAdapter(this, R.layout.raw_new_product, arrayProduct);
        rvMainScreen.setHasFixedSize(true);
        rvMainScreen.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvMainScreen.setAdapter(adapterProduct);

        arrayProduct.add(new Product("asd", 1, "https://dogotinhsau.com/wp-content/uploads/2017/12/IMG_20171126_072940-400x500.jpg",
                "Bộ bàn ghế gụ ta tay 10", 30000000));
    }

}
