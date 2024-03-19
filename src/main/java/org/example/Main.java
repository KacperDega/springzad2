package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleRepository test = new VehicleRepository();
        test.getVehicles("plik.csv");

        List<Vehicle> listaPojazduw = test.getVehicleList();


        //listaPojazduw.add(new Motorcycle("Motur", "Szybki", 2025, 9999, false, "L0 SPEED", "AAA"));

        System.out.println("Odczytane z pliku: ");
        for (Vehicle i : listaPojazduw) {
            System.out.println(i.toString());
        }

        System.out.println();
        test.rentCar("LRY B3C53K","plik.csv");
        System.out.println("po rentCar(): "+listaPojazduw.get(1).toString());
        test.returnCar("LU 14152","plik.csv");
        System.out.println("po returnCar(): "+listaPojazduw.get(0).toString());
    }
}