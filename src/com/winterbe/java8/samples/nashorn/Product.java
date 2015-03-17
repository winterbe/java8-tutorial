package com.winterbe.java8.samples.nashorn;

/**
 * @author Benjamin Winterberg
 */
public class Product {
    private String name;
    private double price;
    private int stock;
    private double valueOfGoods;

    public double getValueOfGoods() {
        return valueOfGoods;
    }

    public void setValueOfGoods(double valueOfGoods) {
        this.valueOfGoods = valueOfGoods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}