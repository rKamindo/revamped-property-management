package com.kamindo.propertymanagement;

import lombok.Data;

@Data
public class AddUnitCommand {
    private String unitNumber;
    private UnitType unitType;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double size;

    private Double rent;
    private Double deposit;

}
