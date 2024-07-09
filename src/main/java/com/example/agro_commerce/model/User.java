package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private Integer password;
    private String sex;
    private LocalDate birthDate;

    public User(String userEmail, String userName, String password, Date birthDate, String sex) {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId: " + userId +
                ", user name: " + userName +
                ", email: " + email +
                ", password: " + password +
                ", sex: " + sex +
                "birth of date: " + birthDate+
                '}';
    }
}
