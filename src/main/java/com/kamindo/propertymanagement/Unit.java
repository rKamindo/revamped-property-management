package com.kamindo.propertymanagement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Unit {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "propertyId")
    private Property property;
    private String unitNumber;
    private UnitType unitType;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double size;
    private Double rent;
    private Double deposit;
}
