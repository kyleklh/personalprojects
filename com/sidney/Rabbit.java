package com.sidney;

public class Rabbit implements Animal{
    public void eat(Food food) {
        System.out.println("eating" + food.foodName());
    }
    
    public void run() {
        System.out.println("running 1km");
    }


}
