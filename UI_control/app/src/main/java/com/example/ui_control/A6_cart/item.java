package com.example.ui_control.A6_cart;

public class item {
    private String name;
    private String price;
    private int src;
    private int quntity = 1;
    private int totle;

    public item(String name, String price, int src) {
        this.name = name;
        this.price = price;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getSrc() {
        return src;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public int getTotle() {
        return totle;
    }

    public void setTotle(int totle) {
        this.totle = totle;
    }
}
