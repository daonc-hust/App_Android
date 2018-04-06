package com.example.congdao.funchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cardViewLogin;
    EditText edtUser, edtPass;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        cardViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPass.getText().toString().trim().equals("jklhui") && edtUser.getText().toString().trim().equals("1")) {
                    cardViewLogin.startAnimation(anim);
                    startActivity(new Intent(MainActivity.this, ChatActivity.class));
                } else if (edtPass.getText().toString().isEmpty() || edtUser.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tài khoản và mật khẩu để đăng nhập", Toast.LENGTH_SHORT).show();
                } else if (!edtUser.getText().toString().trim().equals("1")) {
                    Toast.makeText(MainActivity.this, "Tài khoản không đúng!", Toast.LENGTH_SHORT).show();
                } else if (!edtPass.getText().toString().trim().equals("jklhui")) {
                    Toast.makeText(MainActivity.this, "Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
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
