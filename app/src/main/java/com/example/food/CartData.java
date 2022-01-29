package com.example.food;

public class CartData {
    int id,quantity;
    long orderNumber,menuId;
    double subTotal;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
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

    public CartData(int id, int quantity, long orderNumber, long menuId, double subTotal, String name) {
        this.id = id;
        this.quantity = quantity;
        this.orderNumber = orderNumber;
        this.menuId = menuId;
        this.subTotal = subTotal;
        this.name = name;
    }
}
