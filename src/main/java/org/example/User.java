package org.example;

public class User {
    private String login;
    private String password;
    private String role;
    private String rentedCarPlate;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getRentedCarPlate() {
        return rentedCarPlate;
    }

    public User(String login, String password, String role, String rentedCarPlate) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rentedCarPlate = rentedCarPlate;
    }

    public String toCSV(){
        return String.format("%s;%s;%s;%s;",login,password,role,rentedCarPlate);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", rentedCarPlate='" + rentedCarPlate + '\'' +
                '}';
    }

}