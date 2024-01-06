package com.sidney;

public class Tiger implements Animal {
    public void run() {
        System.out.println("run 5 miles");
    }

    public void eat(Food myFood) {
        System.out.println("eating " + myFood.foodName());
    }



    
}
