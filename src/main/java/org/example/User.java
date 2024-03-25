package org.example;

public class User {
    private String login;
    private String password;
    private String role;
    private String rentedVehiclePlate;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getRentedVehiclePlate() {
        return rentedVehiclePlate;
    }

    public User(String login, String password, String role, String rentedVehiclePlate) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rentedVehiclePlate = rentedVehiclePlate;
    }

    public void rentCar(String plate){
        rentedVehiclePlate = plate;
    }

    public void returnVehicle(){
        rentedVehiclePlate = "";
    }

    public String toCSV(){
        return String.format("%s;%s;%s;%s;",login,password,role, rentedVehiclePlate);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", rentedCarPlate='" + rentedVehiclePlate + '\'' +
                '}';
    }

}