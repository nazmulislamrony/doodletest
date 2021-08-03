package com.example.projecttest.Model;

public class Parent {
    String category_id, category_name;

    public Parent(String category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }
}
