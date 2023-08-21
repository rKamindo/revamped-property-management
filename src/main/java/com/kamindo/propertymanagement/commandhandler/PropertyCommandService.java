package com.kamindo.propertymanagement.commandhandler;

import com.kamindo.propertymanagement.command.AddTenantCommand;
import com.kamindo.propertymanagement.command.AddUnitCommand;
import com.kamindo.propertymanagement.command.CreatePropertyCommand;
import com.kamindo.propertymanagement.event.PropertyCreatedEvent;
import com.kamindo.propertymanagement.event.TenantMovedInUnit;
import com.kamindo.propertymanagement.model.Property;
import com.kamindo.propertymanagement.model.Tenant;
import com.kamindo.propertymanagement.model.Unit;
import com.kamindo.propertymanagement.repository.PropertyRepository;
import com.kamindo.propertymanagement.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyCommandService {

    private final PropertyRepository propertyRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TenantRepository tenantRepository;

    public Long createProperty(CreatePropertyCommand command) {
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
        eventPublisher.publishEvent(new PropertyCreatedEvent(newProperty.getId()));
        return newProperty.getId();
    }

    public void addTenant(AddTenantCommand command) {
        Tenant tenant = tenantRepository.findById(command.getTenantId())
                .orElseThrow(() -> new RuntimeException(String.format("Tenant by id: %s not found")));

        Property property = propertyRepository.findById(command.getPropertyId())
                .orElseThrow(() -> new RuntimeException(String.format("Property by id: %s not found")));

        Unit propertyUnit = property.getUnits()
                .stream()
                .filter(unit -> unit.getId() == command.getUnitId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Unit by id: %s not found", command.getUnitId())));


        property.moveInTenant(tenant, propertyUnit);
        propertyRepository.save(property);
        eventPublisher.publishEvent(new TenantMovedInUnit(property.getId(), propertyUnit.getId(), tenant.getId()));
    }



}
