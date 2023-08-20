package com.kamindo.propertymanagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {

    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody CreatePropertyCommand command) {
        Property property = Property
                .builder()
                .name(command.getName())
                .address(command.getAddress())
                .propertyType(command.getPropertyType())
                .build();

        if (command.getUnits() != null && !command.getUnits().isEmpty()) {
            for (AddUnitCommand addUnitCommand : command.getUnits()) {
                Unit unit = Unit
                        .builder()
                        .unitNumber(addUnitCommand.getUnitNumber())
                        .unitType(addUnitCommand.getUnitType())
                        .rent(addUnitCommand.getRent())
                        .deposit(addUnitCommand.getDeposit())
                        .size(addUnitCommand.getSize())
                        .bedrooms(addUnitCommand.getBedrooms())
                        .bathrooms(addUnitCommand.getBathrooms())
                        .property(property)
                        .build();

                property.addUnit(unit);
            }
        }

        Property newProperty = propertyRepository.save(property);
        return new ResponseEntity<>(newProperty.getId(), HttpStatus.OK);
    }
}
