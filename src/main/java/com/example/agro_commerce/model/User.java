package com.example.agro_commerce.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private Integer userId;
    @NonNull private String userName;
    @NonNull private String email;
    @NonNull private String password;
    @NonNull private String sex;
    @NonNull private LocalDate birthDate;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
