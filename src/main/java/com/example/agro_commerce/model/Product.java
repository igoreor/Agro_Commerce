package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private Integer productId;
    private String type;
    private String name;
    private BigDecimal value;
    private String description;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
