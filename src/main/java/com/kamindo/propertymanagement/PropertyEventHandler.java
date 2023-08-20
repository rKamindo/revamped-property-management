package com.kamindo.propertymanagement;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyEventHandler {
    private final PropertyRepository propertyRepository;

    @EventHandler
    public void on(PropertyCreatedEvent event) {
        Property property = Property
                .builder()
                .name(event.getName())
                .propertyType(event.getPropertyType())
                .propertyStatus(PropertyStatus.ACTIVE)
                .address(event.getAddress())
                .build();

        if (event.getUnits() != null && !event.getUnits().isEmpty()) {
            for (AddUnitCommand addUnitCommand : event.getUnits()) {
                Unit unit = Unit
                        .builder()
                        .unitNumber(addUnitCommand.getUnitNumber())
                        .unitType(addUnitCommand.getUnitType())
                        .property(property)
                        .beds(addUnitCommand.getBeds())
                        .baths(addUnitCommand.getBaths())
                        .size(addUnitCommand.getSize())
                        .rent(addUnitCommand.getRent())
                        .deposit(addUnitCommand.getDeposit())
                        .build();
                property.addUnit(unit);
            }
        }

        propertyRepository.save(property);
    }
}
