package com.kamindo.propertymanagement.event;

import com.kamindo.propertymanagement.model.Address;
import com.kamindo.propertymanagement.model.PropertyStatus;
import com.kamindo.propertymanagement.model.PropertyType;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class PropertyCreatedEvent {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private Address address;
    private PropertyType propertyType;
    private PropertyStatus propertyStatus;
}
