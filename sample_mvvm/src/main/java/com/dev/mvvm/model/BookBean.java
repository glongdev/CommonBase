package com.dev.mvvm.model;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class BookBean {
    private String title;
    private int price;

    public BookBean() {
    }

    public BookBean(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
