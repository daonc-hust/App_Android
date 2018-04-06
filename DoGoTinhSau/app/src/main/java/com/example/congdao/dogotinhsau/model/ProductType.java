package com.example.congdao.dogotinhsau.model;

/**
 * Created by Cong Dao on 3/14/2018.
 */

public class ProductType {
    private String image;
    private String name;

    public ProductType(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
