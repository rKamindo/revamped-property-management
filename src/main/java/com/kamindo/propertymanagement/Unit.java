package com.kamindo.propertymanagement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "propertyId")
    private Property property;

    private String unitNumber;
    @Enumerated(EnumType.STRING)
    private UnitType unitType;
    private Short beds;
    private Short baths;
    private Double size;
    private Double rent;
    private Double deposit;


}
