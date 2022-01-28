package com.example.food;

public class Category {
    long Id;
    String Name;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Category() {
    }

    public Category(long id, String name) {
        Id = id;
        Name = name;
    }
}
