package com.example.congdao.minichat;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;

public class MainActivity extends AppCompatActivity {

    CardView cardViewLogin;
    static EditText edtUser, edtPass;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtPass.getText().toString().isEmpty() || edtUser.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tài khoản và mật khẩu để đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    cardViewLogin.startAnimation(anim);
                    startActivity(new Intent(MainActivity.this, ChatActivity.class));
                    Toast.makeText(MainActivity.this, "Xin chào " + edtUser.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void mapping() {
        cardViewLogin = (CardView) findViewById(R.id.cardViewLogin);
        edtUser = (EditText) findViewById(R.id.editTextUser);
        edtPass = (EditText) findViewById(R.id.editTextPass);

        anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
    }
}
