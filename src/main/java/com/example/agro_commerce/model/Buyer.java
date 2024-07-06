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
    private Integer buyer_id;
    private Integer user_id;
    @Override
    public String toString() {
        return "Buyer{" +
                "buyer_id=" + buyer_id +
                ", user_id=" + user_id +
                '}';
    }
}
