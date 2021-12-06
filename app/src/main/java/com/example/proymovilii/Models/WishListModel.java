package com.example.proymovilii.Models;

public class WishListModel {
    private String key, wish;

    public WishListModel() {
    }

    public WishListModel(String key, String wish) {
        this.key = key;
        this.wish = wish;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
