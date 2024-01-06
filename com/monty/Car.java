package com.monty;

public class Car {

/* 
    protected String brand = "Ford";        // Vehicle attribute
    public void honk() {                    // Vehicle method
        System.out.println("Tuut, tuut!");
    }
*/










    protected int numWheel;

    /*Car (int numWheel) {
        this.numWheel = numWheel;
    }*/

    public void drive () {
        System.out.println("driving on " + numWheel+ " wheels");

    }

    public void setNumWheel (int numWheel) {
        this.numWheel = numWheel;
    }

    public int getNumWheel() {
        return numWheel;
    } 
    
}
