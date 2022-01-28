package com.example.food;

public class MenuCategory {
    long CatId,MenuId;

    public long getCatId() {
        return CatId;
    }

    public void setCatId(long catId) {
        CatId = catId;
    }

    public long getMenuId() {
        return MenuId;
    }

    public void setMenuId(long menuId) {
        MenuId = menuId;
    }

    public MenuCategory() {
    }

    public MenuCategory(long catId, long menuId) {
        CatId = catId;
        MenuId = menuId;
    }
}
