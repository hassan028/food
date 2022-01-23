package com.example.food;

import android.os.Parcel;
import android.os.Parcelable;

public class Product {
    String name,id,price,userId,img;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Product(String name, String id, String price, String userId, String img) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.userId = userId;
        this.img = img;
    }
}