package com.kamindo.propertymanagement.query;

import com.kamindo.propertymanagement.model.PropertyStatus;
import com.kamindo.propertymanagement.model.PropertyType;
import com.kamindo.propertymanagement.model.Address;

public record PropertyDTO (
        String id,
        String name,
        Address address,
        PropertyType propertyType,
        PropertyStatus propertyStatus,
        Long unitsCount
) {}
