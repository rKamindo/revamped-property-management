package com.kamindo.propertymanagement;

import lombok.Data;
import lombok.Value;

@Data
public class AddUnitCommand {
    private String id;

    private String propertyId;
    private String unitNumber;
    private UnitType unitType;
    private Integer beds;
    private Integer baths;
    private Double size;
    private Double rent;
    private Double deposit;

}
