package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    User updateUser(User user, long id);

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getUsers();
}