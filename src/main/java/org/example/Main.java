package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VehicleRepository vehicleRepo = new VehicleRepository();
        vehicleRepo.getVehicles("cars.csv");
        List<Vehicle> listaPojazduw = vehicleRepo.getVehicleList();

        UserRepository usersRepo = new UserRepository();
        usersRepo.getUsers("users.csv");
        List<User> usersList = usersRepo.getUserList();

        // System.out.println(usersList.get(1).getLogin() + "\t" + Authentication.checkCredentials("admin",usersList.get(1).getPassword()));

        Scanner userInput = new Scanner(System.in);

        User currentUser;
        System.out.println();
        while (true) {
            System.out.println("LOG INTO ACCOUNT");

            System.out.print("Login: ");
            String inputLogin = userInput.nextLine();

            System.out.print("Password: ");
            String inputPasswd = userInput.nextLine();
            System.out.println();

            currentUser = usersRepo.getUser(inputLogin);
            if (currentUser != null) {
                if (Authentication.checkCredentials(inputPasswd, currentUser.getPassword()) == false) {
                    System.out.println("Wrong password.\n");
                } else {
                    System.out.println("Successfully logged in.\n\n");
                    break;
                }
            } else {
                System.out.println("Username not found.\n");
            }
        }

        String userMenu = "\n1  Rent car \n2  Return car \n3  User info \n0  Exit";
        String adminMenu = "\n1  Rent car \n2  Return car \n3  User info \n4  Add vehicle \n5  Remove vehicle \n6  See list of users \n7  See list of vehicles \n0  Exit";

        switch (currentUser.getRole()) {
            case "user":
                while (true) {
                    System.out.println(userMenu);
                    int choice = userInput.nextInt();

                    if (choice == 0) {
                        break;
                    } else {
                        runUser(choice, currentUser, vehicleRepo, usersRepo);
                    }
                }
                break;

            case "admin":
                while (true) {
                    System.out.println(adminMenu);
                    int choice = userInput.nextInt();

                    if (choice == 0) {
                        break;
                    } else if (choice >= 1 && choice <= 3) {
                        runUser(choice, currentUser, vehicleRepo, usersRepo);
                    } else {
                        runAdmin(choice, vehicleRepo, usersRepo);
                    }
                }
                break;
        }


//        listaPojazduw.add(new Motorcycle("Motuur", "Szybki", 2025, 9999, false, "L0 SPEED", "AAA"));

//        System.out.println("Odczytane z pliku: ");
//        for (Vehicle i : listaPojazduw) {
//            System.out.println(i.toString());
//        }
//
//        System.out.println();
//        vehicleRepo.rentCar("LRY B3C53K","cars.csv");
//        System.out.println("po rentCar(): "+listaPojazduw.get(1).toString());
//        vehicleRepo.returnCar("LU 14152","cars.csv");
//        System.out.println("po returnCar(): "+listaPojazduw.get(0).toString());

//        System.out.println("Odczytane z pliku: ");
//        for (User i : usersList) {
//            System.out.println(i.getLogin() + "\t" + Authentication.hashPassword(i.getPassword()));
//        }
//
//        testusers.add(new User("vehicleRepo","haslo","user","AAAA"));
//        testusers.save("users.csv");
    }

    private static void runUser(int choice, User currentUser, VehicleRepository vehicleRepo, UserRepository usersRepo) {
        switch (choice) {

            // rent car
            case 1:
                if (!currentUser.getRentedVehiclePlate().isEmpty()) {
                    System.out.println("Can't rent if user has already rented a vehicle.");
                    break;
                }

                Scanner userInput = new Scanner(System.in);
                System.out.print("License plate: ");
                String licensePlate = userInput.nextLine();

                Vehicle targetCar = vehicleRepo.getVehicle(licensePlate);
                if (targetCar == null) {
                    System.out.println("Vehicle not found.");
                } else {
                    if (targetCar.isRented())
                        System.out.println("Vehicle already rented.");
                    else {
                        vehicleRepo.rentCar(licensePlate, "cars.csv");
                        currentUser.rentCar(licensePlate);
                        usersRepo.save("users.csv");
                    }
                }
                break;

            //return car
            case 2:
                if (currentUser.getRentedVehiclePlate().isEmpty()) {
                    System.out.println("User has not rented any vehicle.");
                    break;
                }

                vehicleRepo.returnCar(currentUser.getRentedVehiclePlate(), "cars.csv");
                currentUser.returnVehicle();
                usersRepo.save("users.csv");

                break;

            //get user data
            case 3:
                System.out.println(currentUser.getLogin() + "\trented: " + (currentUser.getRentedVehiclePlate().isEmpty() ? "NONE" : currentUser.getRentedVehiclePlate()));
                break;
        }
    }

    private static void runAdmin(int choice, VehicleRepository vehicleRepo, UserRepository usersRepo) {
        switch (choice) {

            case 4:
                Scanner userInput = new Scanner(System.in);

                System.out.print("Enter vehicle type: ");
                String type = userInput.nextLine();
                System.out.print("Enter license plate: ");
                String plate = userInput.nextLine();
                System.out.print("Enter manufacturer: ");
                String manufacturer = userInput.nextLine();
                System.out.print("Enter model: ");
                String model = userInput.nextLine();
                System.out.print("Enter production year: ");
                String year = userInput.nextLine();
                System.out.print("Enter rent price: ");
                String price = userInput.nextLine();
                if (type.equals("Motorcycle")) {
                    System.out.print("Enter driving license category required: ");
                    String category = userInput.nextLine();

                    vehicleRepo.addVehicle(manufacturer, model, Integer.parseInt(year), Integer.parseInt(price), false, plate, category);
                    vehicleRepo.save("cars.csv");
                } else {
                    vehicleRepo.addVehicle(manufacturer, model, Integer.parseInt(year), Integer.parseInt(price), false, plate);
                    vehicleRepo.save("cars.csv");
                }

                System.out.println("Vehicle successfully added.");

                break;

            case 5:
                userInput = new Scanner(System.in);
                System.out.print("Enter license plate of vehicle to remove: ");
                String plateToRemove = userInput.nextLine();
                vehicleRepo.removeVehicle(plateToRemove, "cars.csv");

                for (User i : usersRepo.getUserList()) {
                    if (i.getRentedVehiclePlate().equals(plateToRemove)){
                        i.returnVehicle();
                        usersRepo.save("users.csv");
                    }
                }
                break;

            case 6:
                usersRepo.printUsers();
                break;

            case 7:
                vehicleRepo.printVehicles();
                break;
        }
    }
}

