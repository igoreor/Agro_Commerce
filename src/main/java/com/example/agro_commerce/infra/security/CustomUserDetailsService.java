package com.example.agro_commerce.infra.security;

import com.example.agro_commerce.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        com.example.agro_commerce.model.User usuarioModel = this.repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return new org.springframework.security.core.userdetails.User(usuarioModel.getEmail(), usuarioModel.getPassword(), new ArrayList<>());
    }
}