package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Integer reservationId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer productId;
    private BigDecimal totalPrice;
    private LocalDate reservationIn;

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", productId=" + productId +
                ", totalPrice=" + totalPrice +
                ", reservationDateTime=" +  reservationIn +
                '}';
    }
}
