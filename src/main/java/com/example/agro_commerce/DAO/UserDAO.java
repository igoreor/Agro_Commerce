package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    boolean insertUser(User user) throws SQLException;

    List<User> listAllUsers() throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(User user) throws SQLException;

    User getUser(int userId) throws SQLException;
    Optional<User> findByEmail(String email);
}
