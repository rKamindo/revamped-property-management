package com.kamindo.propertymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Unit> units;

    public void addUnit(Unit unit) {
        if (units == null) {
            units = new ArrayList<>();
        }

        if (this.propertyType == PropertyType.SINGLE_UNIT && units.size() >= 1) {
            throw new RuntimeException("A single-unit property can have only one unit.");
        }

        units.add(unit);
        unit.setProperty(this);
    }

    public void moveInTenant(Tenant tenant, Unit propertyUnit) {
        propertyUnit.addTenant(tenant);
    }
}
