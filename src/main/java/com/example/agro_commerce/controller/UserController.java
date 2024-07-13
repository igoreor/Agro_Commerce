package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.UserDAO;
import com.example.agro_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            if (userDAO.insertUser(user)) {
                return ResponseEntity.ok("User created successfully");
            } else {
                return ResponseEntity.status(500).body("Failed to create user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        try {
            List<User> users = userDAO.listAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            User user = userDAO.getUser(userId);
            if (userDAO.deleteUser(user)) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.status(500).body("Failed to delete user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User user) {
        try {
            user.setUserId(userId);
            if (userDAO.updateUser(user)) {
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(500).body("Failed to update user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        try {
            User user = userDAO.getUser(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
