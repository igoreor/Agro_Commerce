package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private Integer senha;
    private String sexo;
    private LocalDateTime dataNascimento;

    @Override
    public String toString() {
        return "User{" +
                "userId: " + userId +
                ", userName: " + userName +
                ", email: " + email +
                ", senha: " + senha +
                ", sexo: " + sexo +
                "data de nascimento: " + dataNascimento+
                '}';
    }
}
