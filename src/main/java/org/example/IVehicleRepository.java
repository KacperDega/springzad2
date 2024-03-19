package org.example;

public interface IVehicleRepository {
    void rentCar(String plate,String filename);

    void returnCar(String plate,String filename);

    void getVehicles(String filename);

    void save(String filename);
}
