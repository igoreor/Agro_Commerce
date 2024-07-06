package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private Integer senha;

    @Override
    public String toString() {
        return "User{" +
                "userId: " + userId +
                ", userName: " + userName +
                ", email: " + email +
                ", senha: " + senha +
                '}';
    }
}
