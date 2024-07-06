package com.example.agro_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Integer contactId;
    private Integer sellerId;
    private Integer buyerId;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
