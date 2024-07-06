package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Stock {
    private Integer stockId;
    private Integer sellerId;
    private Integer productId;
    private  Integer amount;

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", sellerId=" + sellerId +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }

}
