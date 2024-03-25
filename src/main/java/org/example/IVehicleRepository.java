package org.example;

public interface IVehicleRepository {
    void rentCar(String plate,String filename);

    void returnCar(String plate,String filename);

    void getVehicles(String filename);

    void save(String filename);

    void addVehicle(String brand, String model, int year, double price, boolean rented, String plate);

    void addVehicle(String brand, String model, int year, double price, boolean rented, String plate, String category);

    void removeVehicle(String plate, String filename);
}
