package com.sidney;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Tiger();
        Animal myAnimal2 = new Rabbit();
        Food food1 = new Carrot();
        Food food2 = new Steak();

        feed(myAnimal, food1);
        run(myAnimal);

        feed(myAnimal2, food2);
        run(myAnimal2);
    }

    public static void feed(Animal animal, Food someFood) {
        animal.eat(someFood);
    }

    public static void run(Animal animal){
        animal.run();
    }
    
}
