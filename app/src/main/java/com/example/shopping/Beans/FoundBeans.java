package com.example.shopping.Beans;

public class FoundBeans {
    private String title;
    private int image;

    public FoundBeans(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public FoundBeans() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
