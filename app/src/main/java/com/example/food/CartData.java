package com.example.food;

public class CartData {
    int quantity;
    long menuId;
    double subTotal;
    String name;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartData() {
    }

    public CartData( int quantity, long menuId, double subTotal, String name) {
        this.quantity = quantity;

        this.menuId = menuId;
        this.subTotal = subTotal;
        this.name = name;
    }
}
