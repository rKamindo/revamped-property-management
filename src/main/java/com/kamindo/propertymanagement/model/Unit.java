package com.kamindo.propertymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "unit")
    private List<Tenant> tenants;

    public void addTenant(Tenant tenant) {
        if (tenants == null) {
            tenants = new ArrayList<>();
        }

        tenants.add(tenant);
        tenant.setUnit(this);
    }
}
