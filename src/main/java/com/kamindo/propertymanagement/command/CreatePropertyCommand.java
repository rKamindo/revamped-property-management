package com.kamindo.propertymanagement.command;

import com.kamindo.propertymanagement.model.PropertyType;
import com.kamindo.propertymanagement.model.Address;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
public class CreatePropertyCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;

    private Address address;
    private PropertyType propertyType;
    private List<AddUnitCommand> units;
}
