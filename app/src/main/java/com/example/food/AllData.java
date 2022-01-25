package com.example.food;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllData extends Application {
    static List<Product> popularProduct;
    static List<Cart> cart;

    public AllData() {
        popularProduct=new ArrayList<>();
        cart=new ArrayList<>();
    }

    public static List<Product> getPopularProduct() {
        return popularProduct;
    }

    public static void setPopularProduct(List<Product> popularProduct) {
        AllData.popularProduct = popularProduct;
    }

    public static List<Cart> getCart() {
        return cart;
    }

    public static void setCart(List<Cart> cart) {
        AllData.cart = cart;
    }

}