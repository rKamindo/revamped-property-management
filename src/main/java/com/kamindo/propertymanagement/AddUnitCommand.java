package com.kamindo.propertymanagement;

import lombok.Data;

import java.util.UUID;

@Data
public class AddUnitCommand {

    private UUID propertyId;
    private String unitNumber;
    private UnitType unitType;
    private Short beds;
    private Short baths;
    private Double size;
    private Double rent;
    private Double deposit;

}
