package org.example;

public class Car extends Vehicle{

    public Car(String brand, String model, int year, double price, boolean rented, String plate) {
        super(brand, model, year, price, rented, plate);
    }

    @Override
    public String toCSV() {
        return "Car;" + super.toCSV();
    }
}
