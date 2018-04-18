package com.example.congdao.fingerpaint;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageButton imbSave, imbEraser, imbColorWheel;
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imbSave = findViewById(R.id.imageButtonSave);
        imbEraser = findViewById(R.id.imageButtonEraser);
        imbColorWheel = findViewById(R.id.imageButtonColorWheel);
        paintView = findViewById(R.id.paintView);

        imbEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(Color.WHITE);
                Toast.makeText(MainActivity.this, "Tẩy", Toast.LENGTH_SHORT).show();
            }
        });

        imbColorWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setColor(Color.RED);
                Toast.makeText(MainActivity.this, "Bút mực đỏ", Toast.LENGTH_SHORT).show();
            }
        });

        imbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.setDrawingCacheEnabled(true);
                paintView.buildDrawingCache();
                Bitmap bitmap = paintView.getDrawingCache(); // lấy ra dữ liệu vẽ dưới dạng bitmap

                String path = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg"; // đường dẫn để lưu ảnh

                File file = new File(path);

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // nén bitmap thành file (nén 100%)
                    // vào file fos
                    bitmap.recycle(); //giải phóng bitmap
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
