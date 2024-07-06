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

public class Assessment {
    private Integer assessmentId;
    private Integer buyerId;
    private Integer sellerId;
    private Double assessment;
    private LocalDate assessmentDate;

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", assessment=" + assessment +
                ", assessmentDate=" + assessmentDate +
                '}';
    }

}
