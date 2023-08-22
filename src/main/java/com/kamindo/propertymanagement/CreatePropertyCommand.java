package com.kamindo.propertymanagement;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
public class CreatePropertyCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;

    private Address address;
    private PropertyType propertyType;
    private List<AddUnitCommand> units;
}
