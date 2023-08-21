package com.kamindo.propertymanagement.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyCreatedEvent {
    private Long propertyId;
}
