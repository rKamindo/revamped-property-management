package com.kamindo.propertymanagement;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

    @Id
    private String id;
    private String propertyId;

    private String unitNumber;
    @Enumerated(EnumType.STRING)
    private UnitType unitType;
    private Integer beds;
    private Integer baths;
    private Double size;
    private Double rent;
    private Double deposit;


}