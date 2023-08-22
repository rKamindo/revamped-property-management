package com.kamindo.propertymanagement;

import lombok.*;

@Value
public class PropertyCreatedEvent {

    private String id;
    private String name;
    private Address address;
    private PropertyType propertyType;
}
