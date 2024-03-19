package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    @Override
    public void rentCar(String plate, String filename) {
        vehicleList.stream().filter(obj -> obj.getPlate().equals(plate)).findFirst().ifPresent(vehicle ->
                vehicle.setRented(true)
        );

        save(filename);
    }

    @Override
    public void returnCar(String plate, String filename) {
        vehicleList.stream().filter(obj -> obj.getPlate().equals(plate)).findFirst().ifPresent(vehicle ->
                vehicle.setRented(false)
        );

        save(filename);
    }

    @Override
    public void getVehicles(String filename) {
        try {
            BufferedReader fileRead = new BufferedReader(new FileReader(filename));
            vehicleList.clear();

            String line;
            while ((line = fileRead.readLine()) != null) {
                String[] data = line.split(";");

                String plateNumber = data[1];
                String brand = data[2];
                String model = data[3];
                int year = Integer.parseInt(data[4]);
                double price = Double.parseDouble(data[5]);
                boolean rented = Boolean.parseBoolean(data[6]);

                if (data[0].equals("Car")) {
                    vehicleList.add(new Car(brand, model, year, price, rented, plateNumber));
                } else if (data[0].equals("Motorcycle")) {
                    String category = data[7];
                    vehicleList.add(new Motorcycle(brand, model, year, price, rented, plateNumber, category));
                }
            }
            fileRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            BufferedWriter fileWrite = new BufferedWriter(fw);

            for (Vehicle i : vehicleList)
                fileWrite.write(i.toCSV() + "\n");

            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addVehicle(String brand, String model, int year, double price, boolean rented, String plate){
        vehicleList.add(new Car(brand, model, year, price, rented, plate));
    };

    @Override
    public void addVehicle(String brand, String model, int year, double price, boolean rented, String plate, String category){
        vehicleList.add(new Motorcycle(brand, model, year, price, rented, plate, category));
    }

    @Override
    public void removeVehicle(String plate){
        for (Vehicle i : vehicleList){
            if (i.getPlate().equals(plate)){
                vehicleList.remove(i);
                break;
            }
        }
    }
}
