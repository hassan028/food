package com.example.food;

public class Cart {
    int id,menuId,quantity,orderNumber;
    double subtotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Cart() {
    }

    public Cart(int id, int menuId, int quantity, int orderNumber, double subtotal) {
        this.id = id;
        this.menuId = menuId;
        this.quantity = quantity;
        this.orderNumber = orderNumber;
        this.subtotal = subtotal;
    }
}
