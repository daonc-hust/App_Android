package com.example.congdao.sambaby;

import android.widget.ImageView;

/**
 * Created by Cong Dao on 3/5/2018.
 */

public class ImageViewSam {
    private int id;
    private int image;

    public ImageViewSam(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
