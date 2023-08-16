package com.kamindo.propertymanagement.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePropertyCommand {
    private Long id;
    private String name;
    private Address address;
    private PropertyType propertyType;
}
