package com.sidney;

public class Steak implements Food {
    private boolean isVeggie;

    public void isVeggie() {
        isVeggie = false;
    }

    public String foodName() {
        return "steak";
    }
}
