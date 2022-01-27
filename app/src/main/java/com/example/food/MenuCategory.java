package com.example.food;

public class MenuCategory {
    int catId,menuId;

    public int getCartId() {
        return catId;
    }

    public void setCartId(int catId) {
        this.catId = catId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public MenuCategory() {
    }

    public MenuCategory(int catId, int menuId) {
        this.catId = catId;
        this.menuId = menuId;
    }
}
