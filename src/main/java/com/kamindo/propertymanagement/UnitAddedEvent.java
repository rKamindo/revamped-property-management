package com.kamindo.propertymanagement;

import lombok.*;

@Value
public class UnitAddedEvent {
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
