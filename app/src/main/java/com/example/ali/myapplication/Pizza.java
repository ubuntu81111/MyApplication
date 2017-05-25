package com.example.ali.myapplication;

/**
 * Created by ali on 24-May-17.
 */

public class Pizza {
    public static final int SIZE_SMALL = 0, SIZE_MEDIUM = 1 ,SIZE_LARGE = 2;
    public static final int TOPPING_REGULAR = 0, TOPPING_CHEESE = 1 ,TOPPING_CHICHEN = 2,TOPPING_MOZILLA=3;
    public double priceBase, priceTopping;
    private int pizzaSize, topping;
    public Pizza(int pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public void setSize(int pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getSize(){
        return pizzaSize;
    }

    public void setTopping(int topping){
        this.topping = topping;
    }
}
