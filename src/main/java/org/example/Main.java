package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleRepository test = new VehicleRepository();
        test.getVehicles("cars.csv");

        List<Vehicle> listaPojazduw = test.getVehicleList();

        UserRepository testusers = new UserRepository();
        testusers.getUsers("users.csv");
        List<User> userList = testusers.getUserList();

        //listaPojazduw.add(new Motorcycle("Motuur", "Szybki", 2025, 9999, false, "L0 SPEED", "AAA"));

//        System.out.println("Odczytane z pliku: ");
//        for (Vehicle i : listaPojazduw) {
//            System.out.println(i.toString());
//        }
//
//        System.out.println();
//        test.rentCar("LRY B3C53K","cars.csv");
//        System.out.println("po rentCar(): "+listaPojazduw.get(1).toString());
//        test.returnCar("LU 14152","cars.csv");
//        System.out.println("po returnCar(): "+listaPojazduw.get(0).toString());

//        System.out.println("Odczytane z pliku: ");
//        for (User i : userList) {
//            System.out.println(i.toString());
//        }
//
//        testusers.add(new User("test","haslo","user","AAAA"));
//        testusers.save("users.csv");
    }
}