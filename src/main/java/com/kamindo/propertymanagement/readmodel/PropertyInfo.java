package com.kamindo.propertymanagement.readmodel;

import com.kamindo.propertymanagement.model.Address;
import com.kamindo.propertymanagement.model.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyInfo {
    private String name;
    private Address address;
    private PropertyType propertyType;
    private Long units;
}
