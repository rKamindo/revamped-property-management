package com.kamindo.propertymanagement;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyCreatedEvent {
    private UUID id;
    private String name;

    private Address address;
    private PropertyType propertyType;

    private PropertyStatus propertyStatus;
    private List<AddUnitCommand> units;
}
