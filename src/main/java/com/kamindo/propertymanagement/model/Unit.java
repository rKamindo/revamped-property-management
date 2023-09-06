package com.kamindo.propertymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id) && Objects.equals(propertyId, unit.propertyId) && Objects.equals(unitNumber, unit.unitNumber) && unitType == unit.unitType && Objects.equals(beds, unit.beds) && Objects.equals(baths, unit.baths) && Objects.equals(size, unit.size) && Objects.equals(rent, unit.rent) && Objects.equals(deposit, unit.deposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyId, unitNumber, unitType, beds, baths, size, rent, deposit);
    }
}
