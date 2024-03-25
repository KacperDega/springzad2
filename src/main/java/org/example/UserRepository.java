package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private List<User> userList;

    public UserRepository() {
        userList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public void getUsers(String filename) {
        try {
            BufferedReader fileRead = new BufferedReader(new FileReader(filename));
            userList.clear();

            String line;
            while ((line = fileRead.readLine()) != null) {
                String[] data = line.split(";");

                String login = data[0];
                String password = data[1];
                String role = data[2];
                //String rentedPlate = data[3];

                if (data.length == 3) {
                    userList.add(new User(login, password, role, ""));
                } else {
                    userList.add(new User(login, password, role, data[3]));
                }
            }
            fileRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String login) {
        for (User i : userList) {
            if (i.getLogin().equals(login)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename, false);
            BufferedWriter fileWrite = new BufferedWriter(fw);

            for (User i : userList)
                fileWrite.write(i.toCSV() + "\n");

            fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void printUsers(){
        for (User i : userList){
            System.out.println(i.toString());
        }
    }
}
