package com.example.congdao.merrychristmas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgLove;
    Button btnPlay;
    public static MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                song.stop();

            }
        });
    }

    private void mapping() {
        final Animation animImage = AnimationUtils.loadAnimation(this, R.anim.anim_image);
        final Animation animButton = AnimationUtils.loadAnimation(this, R.anim.anim_button);

        song = MediaPlayer.create(MainActivity.this, R.raw.merry);
        song.start();

        imgLove = (ImageView) findViewById(R.id.imageViewLove);
        imgLove.startAnimation(animImage);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnPlay.startAnimation(animButton);
    }
}
