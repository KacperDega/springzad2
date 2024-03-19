package org.example;

public class Motorcycle extends Vehicle {
    private String category;

    public Motorcycle(String brand, String model, int year, double price, boolean rented, String plate, String category) {
        super(brand, model, year, price, rented, plate);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toCSV() {
        return "Motorcycle;" + super.toCSV() + category + ";";
    }

    @Override
    public String toString(){
        String basicString = super.toString();
        return basicString.substring(0,basicString.length()-1) + ", category=" + category + '}';
    }
}
