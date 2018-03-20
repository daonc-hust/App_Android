package com.example.congdao.merrychristmas;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    ListView lvMusic;
    TextView txtNoel, txtLove;
    ArrayList<Music> musicArrayList;
    MusicAdapter musicAdapter;
    ArrayList<Integer> mediaPlayers;
    ArrayList<String> lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mapping();
        creatArray();

        musicAdapter = new MusicAdapter(R.layout.music, this, musicArrayList);
        lvMusic.setAdapter(musicAdapter);

        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.song.stop();
                MainActivity.song = MediaPlayer.create(getApplicationContext(), mediaPlayers.get(i));
                MainActivity.song.start();
                txtLove.setText(lyrics.get(i));
            }
        });

        txtNoel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MusicActivity.this, MainActivity.class));
                MainActivity.song.stop();
            }
        });
    }

    private void mapping() {
        final Animation animText = AnimationUtils.loadAnimation(this, R.anim.anim_text);

        txtNoel = (TextView) findViewById(R.id.textViewNoel);
        txtLove = (TextView) findViewById(R.id.textViewLove);
        txtLove.startAnimation(animText);
        lvMusic = (ListView) findViewById(R.id.listViewMusic);
    }

    private void creatArray() {
        //mang chua bai hat
        mediaPlayers = new ArrayList<Integer>();
        mediaPlayers.add(R.raw.dusk_till_dawn);
        mediaPlayers.add(R.raw.save_me);
        mediaPlayers.add(R.raw.all_we_know);
        mediaPlayers.add(R.raw.some_thing_just_like_this);
        mediaPlayers.add(R.raw.da_lo_yeu_em_nhieu);
        mediaPlayers.add(R.raw.ta_con_yeu_nhau);
        mediaPlayers.add(R.raw.anh_nang_cua_anh);
        mediaPlayers.add(R.raw.we_cant_stop);

        //mang chua mo ta bai hat
        musicArrayList = new ArrayList<>();
        musicArrayList.add(new Music("Dusk till dawn", "ZAYN ft Sia"));
        musicArrayList.add(new Music("Save me", "Deamn"));
        musicArrayList.add(new Music("All we know", "The Chainsmokers ft Phoebe Ryan"));
        musicArrayList.add(new Music("Somethings just like this", "The Chainsmokers ft Coldplay"));
        musicArrayList.add(new Music("Đã lỡ yêu em nhiều", "Justa Tee"));
        musicArrayList.add(new Music("Ta còn yêu nhau", "Đức Phúc"));
        musicArrayList.add(new Music("Ánh nắng của anh", "Đức Phúc"));
        musicArrayList.add(new Music("We can't stop", "Boyce Avenue ft Bea Miller "));

        //mang chua loi bai hat
        lyrics = new ArrayList<>();
        lyrics.add("Dusk till dawn");
        lyrics.add("Save me");
        lyrics.add("All we know");
        lyrics.add("Somethings just like this");
        lyrics.add("Đã lỡ yêu em nhiều");
        lyrics.add("Ta còn yêu nhau");
        lyrics.add("Ánh nắng của anh");
        lyrics.add("We can't stop");
    }
}
