package com.example.congdao.dogotinhsau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.congdao.dogotinhsau.R;
import com.example.congdao.dogotinhsau.model.Basket;
import com.example.congdao.dogotinhsau.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetails extends AppCompatActivity {

    Toolbar toolbarDetails;
    TextView txtName, txtPrice, txtDescribe;
    ImageView imgProductDetails;
    Spinner spinner;
    Button btnOrder;
    String describe = "";
    int id = 0;
    String imageDetails = "";
    String nameDetails = "";
    Integer priceDetails = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Mapping();
        ActionToolBar();

        GetInformationProduct();
        CatchEventSpinner();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNumber = Integer.parseInt(spinner.getSelectedItem().toString());
                boolean exists = false;
                if (MainActivity.basketArrayList.size() > 0) {
                    for (int i = 0; i < MainActivity.basketArrayList.size(); i++) {
                        if (MainActivity.basketArrayList.get(i).getId() == id) {
                            MainActivity.basketArrayList.get(i).setNumber(MainActivity.basketArrayList.get(i).getNumber() + newNumber);
                            MainActivity.basketArrayList.get(i).setPrice(priceDetails * MainActivity.basketArrayList.get(i).getNumber());
                            exists = true;
                        }

                        if (exists == false) {
                            int number = Integer.parseInt(spinner.getSelectedItem().toString());
                            long newPrice = number * priceDetails;
                            MainActivity.basketArrayList.add(new Basket(id, nameDetails, newPrice, imageDetails, number));
                        }
                    }

                } else {
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    long newPrice = number * priceDetails;
                    MainActivity.basketArrayList.add(new Basket(id, nameDetails, newPrice, imageDetails, number));
                }

                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] number = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, number);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformationProduct() {


        Product product = (Product) getIntent().getSerializableExtra("ThongTinSanPham");
        describe = product.getDescribe();
        id = product.getId();
        imageDetails = product.getImage();
        nameDetails = product.getName();
        priceDetails = product.getPrice();

        txtName.setText(nameDetails);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice.setText("Giá : " + decimalFormat.format(priceDetails) + " Đ");
        txtDescribe.setText(describe);

        Picasso.with(getApplicationContext()).load(imageDetails)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error)
                .into(imgProductDetails);

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Mapping() {
        toolbarDetails = (Toolbar) findViewById(R.id.toolbarProductDetails);
        txtName = (TextView) findViewById(R.id.textViewNameProductDetails);
        txtPrice = (TextView) findViewById(R.id.textViewPriceDetails);
        txtDescribe = (TextView) findViewById(R.id.textViewDescribe);
        imgProductDetails = (ImageView) findViewById(R.id.imageViewProductDetails);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnOrder = (Button) findViewById(R.id.buttonOrder);
    }
}
