package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.UserDAO;
import com.example.agro_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<User> getAllUsers() throws SQLException {
        return userDAO.listAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) throws SQLException {
        return userDAO.getUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws SQLException {
        if (userDAO.insertUser(user)) {
            return user;
        } else {
            throw new RuntimeException("Failed to create user.");
        }
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody User user) throws SQLException {
        user.setUserId(userId);
        if (userDAO.updateUser(user)) {
            return user;
        } else {
            throw new RuntimeException("Failed to update user.");
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) throws SQLException {
        User user = userDAO.getUser(userId);
        if (user != null) {
            userDAO.deleteUser(user);
        } else {
            throw new RuntimeException("User not found.");
        }
    }
}
