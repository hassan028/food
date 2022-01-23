package com.example.food;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllData extends Application {
    static List<Product> popularProduct;

    public AllData() {
        popularProduct=new ArrayList<>();
    }

    public AllData(List<Product> popularProduct) {
        this.popularProduct = popularProduct;
    }

    public static List<Product> getPopularProduct() {
        return popularProduct;
    }

    public static void setPopularProduct(List<Product> popularProduct) {
        AllData.popularProduct = popularProduct;
    }
}