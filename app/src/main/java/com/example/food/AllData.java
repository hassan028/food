package com.example.food;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AllData extends Application {
    static List<CartData> cartList;
    static List<Order> orderList;
    static List<Category> categoryList;
    static List<Product> menuList;
    static List<Product> filteredList;
    public AllData() {

        cartList=new ArrayList<CartData>();
        categoryList=new ArrayList<>();
        menuList = new ArrayList<>();
        orderList = new ArrayList<>();
        filteredList=new ArrayList<>();
    }
    public static List<Product>  getCategoryProductList(String catName) {
        List<Product>  categoryProductList = new ArrayList<>();

        if(catName.equals("Popular")){
            categoryProductList = getPopularList();
            return  categoryProductList;
        }

        long categoryId = -1;
        for (int i = 0; i<categoryList.size(); i++){
            if(categoryList.get(i).getName().equals(catName)){
                categoryId = categoryList.get(i).getId();
                break;
            }
        }
        for(int i = 0; i < menuList.size(); i++ ){
            if(menuList.get(i).CatId == categoryId){
                categoryProductList.add(menuList.get(i));
            }
        }

        return  categoryProductList;

    }
    public static String getCategoryName(Product tempProd){
        for (Category tempCat : categoryList){
            if(tempProd.getCatId() == tempCat.getId()){
                return tempCat.getName();
            }
        }
        return null;
    }

    public static List<Product> getPopularList() {
        List<Product>  popularList = new ArrayList<>();

        for(int i = 0; i < menuList.size(); i++ ){
            if(menuList.get(i).getOrderCount() > 10){
                popularList.add(menuList.get(i));

            }
        }
        return  popularList;
    }

    /*public static List<Product>  getCategoryProductList(String catName) {
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

    }*/


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