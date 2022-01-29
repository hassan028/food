package com.example.food;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllData extends Application {
    static List<Cart> cartList;
    static List<Order> orderList;
    static List<Category> categoryList;
    static List<MenuCategory> menuCategoryList;
    static List<Product> menuList;

    public AllData() {

        cartList=new ArrayList<>();
        categoryList=new ArrayList<>();
        menuCategoryList=new ArrayList<>();
        menuList = new ArrayList<>();
        orderList = new ArrayList<>();
    }

    public static List<Product>  getCategoryProductList(String catName) {
        List<Product>  categoryProductList = new ArrayList<>();
        long idPopular = -1;
        long menuId;
        Product tempProduct;
        for(int i = 0; i < categoryList.size(); i++){
            if( categoryList.get(i).getName().equals(catName)){
                idPopular = categoryList.get(i).getId();
                break;
            }
        }

        for(int i = 0; i < menuCategoryList.size(); i++){
            if(menuCategoryList.get(i).getCatId() == idPopular){
                menuId = menuCategoryList.get(i).getMenuId();
                for (int j=0; j < menuList.size(); j++){
                    if(menuId == menuList.get(j).getId()){
                        tempProduct = menuList.get(j);
                        categoryProductList.add(tempProduct);
                    }
                }
            }
        }

        return  categoryProductList;

    }


    /*public static void setPopularProduct() {
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
    }*/

}