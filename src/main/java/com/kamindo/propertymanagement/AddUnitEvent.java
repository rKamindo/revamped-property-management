package com.kamindo.propertymanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUnitEvent {
    private UUID id;
    private UUID propertyId;
    private String unitNumber;
    private UnitType unitType;
    private Short beds;
    private Short baths;
    private Double size;
    private Double rent;
    private Double deposit;

}
