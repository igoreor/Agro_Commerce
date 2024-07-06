package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Buyer {
    private Integer buyerId;
    private Integer userId;

    @Override
    public String toString() {
        return "Buyer{" +
                "buyer_id=" + buyerId +
                ", user_id=" + userId +
                '}';
    }
}
