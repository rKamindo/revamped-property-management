package com.kamindo.propertymanagement;

import lombok.Data;

import java.util.List;

@Data
public class CreatePropertyCommand {
    private String name;
    private Address address;
    private PropertyType propertyType;
    private List<AddUnitCommand> units;
}
