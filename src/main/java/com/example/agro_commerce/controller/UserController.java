package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.UserDAO;
import com.example.agro_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        try {
            if (userDAO.insertUser(user)) {
                return "User added successfully!";
            } else {
                return "Failed to add user.";
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        try {
            return userDAO.listAllUsers();
        } catch (SQLException e) {
            // Handle exception accordingly
            return null;
        }
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try {
            if (userDAO.updateUser(user)) {
                return "User updated successfully!";
            } else {
                return "Failed to update user.";
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId) {
        try {
            User user = userDAO.getUser(userId);
            if (user != null) {
                if (userDAO.deleteUser(user)) {
                    return "User deleted successfully!";
                } else {
                    return "Failed to delete user.";
                }
            } else {
                return "User not found.";
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        try {
            return userDAO.getUser(userId);
        } catch (SQLException e) {
            return null;
        }
    }
}
