package com.kamindo.propertymanagement.property;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private Address address;
    private PropertyType propertyType;
}