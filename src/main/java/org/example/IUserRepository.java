package org.example;

public interface IUserRepository {
    void getUsers(String filename);

    User getUser(String login);

    void save(String filename);
}
