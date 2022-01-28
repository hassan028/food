package com.example.food;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllData extends Application {
    static List<Product> popularProduct;
    static List<Cart> cart;
    static List<Order> order;
    static List<Category> category;
    static List<MenuCategory> menuCategory;
    static List<Product> menu;

    public AllData() {
        popularProduct=new ArrayList<>();
        cart=new ArrayList<>();
        category=new ArrayList<>();
        menuCategory=new ArrayList<>();
        menu=new ArrayList<>();
        order=new ArrayList<>();
    }

    //category getters and setters
    public static List<Category> getCategory() {
        return category;
    }

    public static void setCategory(List<Category> category) {
        AllData.category = category;
    }

//popular product getters and setters
    public static List<Product> getPopularProduct() {
        return popularProduct;
    }

    public static void setPopularProduct(List<Product> popularProduct) {
        AllData.popularProduct = popularProduct;
    }

//cart getters and setters
    public static List<Cart> getCart() {
        return cart;
    }

    public static void setCart(List<Cart> cart) {
        AllData.cart = cart;
    }
//menu getters and setters
    public static List<Product> getMenu() {
        return menu;
    }

    public static void setMenu(List<Product> menu) {
        AllData.menu = menu;
    }

    //menu category setters and getters
    public static List<MenuCategory> getMenuCategory() {
        return menuCategory;
    }

    public static void setMenuCategory(List<MenuCategory> menuCategory) {
        AllData.menuCategory = menuCategory;
    }


    public static void setPopularProduct() {
        long menuId;

        for (int j = 0; j < menuCategory.size(); j++) {
            if (menuCategory.get(j).getCatId() == 1) {
                menuId = menuCategory.get(j).getMenuId();
                for (int i = 0; i < menu.size(); i++) {
                    if ((int)(menu.get(i).getId()) == menuId) {
                        AllData.popularProduct.add(menu.get(i));
                        break;
                    }
                }
            }
        }
    }
//Order List Getters and Setters
    public static List<Order> getOrder() {
        return order;
    }

    public static void setOrder(List<Order> order) {
        AllData.order = order;
    }
}