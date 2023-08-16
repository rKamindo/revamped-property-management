package com.kamindo.propertymanagement.property;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    protected String street;
    protected String city;
    protected String state;

    protected String postalCode;
    protected String country;
    // getters and setters
}