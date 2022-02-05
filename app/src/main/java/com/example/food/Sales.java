package com.example.food;

import java.util.Date;
import java.util.List;

public class Sales {
    int SaleId,OrderNumber;
    double TotalBill;
    String Date;
    List<CartData> cartList;


    public Sales(int saleId, int orderNumber, double totalBill, String date, List<CartData> cartList) {
        SaleId = saleId;
        OrderNumber = orderNumber;
        TotalBill = totalBill;
        Date = date;
        this.cartList = cartList;
    }

    public List<CartData> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartData> cartList) {
        this.cartList = cartList;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getSaleId() {
        return SaleId;
    }

    public void setSaleId(int saleId) {
        SaleId = saleId;
    }

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

    public double getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(double totalBill) {
        TotalBill = totalBill;
    }
}
