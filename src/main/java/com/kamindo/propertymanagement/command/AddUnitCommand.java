package com.kamindo.propertymanagement.command;

import com.kamindo.propertymanagement.model.UnitType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddUnitCommand {
    private String id;
    @TargetAggregateIdentifier

    private String propertyId;
    private String unitNumber;
    private UnitType unitType;
    private Integer beds;
    private Integer baths;
    private Double size;
    private Double rent;
    private Double deposit;

}
