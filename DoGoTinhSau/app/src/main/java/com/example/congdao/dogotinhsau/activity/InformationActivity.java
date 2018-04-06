package com.example.congdao.dogotinhsau.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.congdao.dogotinhsau.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InformationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        toolbarInfo = (Toolbar) findViewById(R.id.toolbarInfo);
        ActionToolbar();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarInfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng doGoTinhSau = new LatLng(21.052621, 105.614641);
        mMap.addMarker(new MarkerOptions().position(doGoTinhSau).title("Đồ Gỗ Tình Sáu").snippet("Số 1, đường Phúc Lợi, thôn 3, khu CN xã Canh Nậu, Thạch Thất, Hà Nội").icon(BitmapDescriptorFactory.defaultMarker()));
        CameraUpdate cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(doGoTinhSau, 10);
        mMap.moveCamera(cameraUpdateFactory);
    }
}
