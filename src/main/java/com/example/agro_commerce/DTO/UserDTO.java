package com.example.agro_commerce.DTO;


import java.time.LocalDate;

public record UserDTO(String userName, String email, String password, String sex, LocalDate birthDate) {
}
