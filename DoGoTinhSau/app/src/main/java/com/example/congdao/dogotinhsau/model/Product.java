package com.example.congdao.dogotinhsau.model;

import java.io.Serializable;

/**
 * Created by Cong Dao on 3/18/2018.
 */

public class Product implements Serializable {
    public String describe;
    public int id;
    public String image;
    public String name;
    public Integer price;

    public Product(String describe, int id, String image, String name, Integer price) {
        this.describe = describe;
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
