package com.example.congdao.dogotinhsau.model;

public class Basket {
    private int id;
    private String name;
    private long price;
    private String image;
    private int number;

    public Basket(int id, String name, long price, String image, int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.number = number;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
