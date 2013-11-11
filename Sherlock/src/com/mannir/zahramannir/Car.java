package com.mannir.zahramannir;

public class Car {

    public int id;
    public String brand;
    public String color;

    // constructor
    public Car(int id, String brand, String color) {
        this.id = id;
        this.brand = brand;
        this.color = color;
    }

    // 2nd constructor
    public Car(String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

}