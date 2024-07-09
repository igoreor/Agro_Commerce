package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Integer locationId;
    private Integer sellerId;
    private String street;
    private String neighborhood;
    private String city;
    private Integer numberHouse;

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", sellerId=" + sellerId +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", numberHouse=" + numberHouse +
                '}';
    }
}
