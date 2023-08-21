package com.kamindo.propertymanagement.command;

import com.kamindo.propertymanagement.model.Address;
import com.kamindo.propertymanagement.model.PropertyType;
import lombok.Data;

import java.util.List;

@Data
public class CreatePropertyCommand {
    private String name;
    private Address address;
    private PropertyType propertyType;
    private List<AddUnitCommand> units;
}
