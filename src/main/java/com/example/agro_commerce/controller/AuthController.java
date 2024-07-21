package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.UserDAO;
import com.example.agro_commerce.DTO.LoginDTO;
import com.example.agro_commerce.DTO.ResponseDTO;
import com.example.agro_commerce.DTO.UserDTO;
import com.example.agro_commerce.infra.security.TokenService;
import com.example.agro_commerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class AuthController {
    private final UserDAO repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        User usuarioModel = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(usuarioModel.getPassword());
        if (passwordEncoder.matches(body.password(), usuarioModel.getPassword())) {
            System.out.println("ENTROU");
            String token = this.tokenService.generateToken(usuarioModel);
            return ResponseEntity.ok(new ResponseDTO(usuarioModel.getUserName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO body) throws SQLException {
        Optional<User> usuarioModel = this.repository.findByEmail(body.email());
        if (usuarioModel.isEmpty()) {
            User user = new User();
            user.setUserName(body.userName());
            user.setEmail(body.email());
            user.setPassword(passwordEncoder.encode(body.password()));
            user.setSex(body.sex());
            user.setBirthDate(LocalDate.now());

            this.repository.insertUser(user);

            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUserName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}




