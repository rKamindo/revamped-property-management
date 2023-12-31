package com.kamindo.propertymanagement.model;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    private  String city;
    private  String state;

    private  String postalCode;
    private  String country;
}