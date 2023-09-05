package com.kamindo.propertymanagement;

import com.kamindo.propertymanagement.command.AddUnitCommand;
import com.kamindo.propertymanagement.command.CreatePropertyCommand;
import com.kamindo.propertymanagement.event.PropertyCreatedEvent;
import com.kamindo.propertymanagement.event.UnitAddedEvent;
import com.kamindo.propertymanagement.exception.SingleUnitPropertyException;
import com.kamindo.propertymanagement.model.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PropertyTest {
    private AggregateTestFixture<Property> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Property.class);
    }

    @Test
    public void createSingleUnitPropertyWithValidRequest() {
        AddUnitCommand unitCommand = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("101")
                .unitType(UnitType.APARTMENT)
                .beds(2)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        CreatePropertyCommand command = CreatePropertyCommand.builder()
                .id("1")
                .name("Test Property")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Anytown")
                        .state("CA")
                        .postalCode("12345")
                        .build())
                .propertyType(PropertyType.SINGLE_UNIT)
                .units(List.of(unitCommand))
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PropertyCreatedEvent(
                        command.getId(),
                        command.getName(),
                        command.getAddress(),
                        command.getPropertyType(),
                        PropertyStatus.ACTIVE
                ), new UnitAddedEvent(
                        unitCommand.getId(),
                        unitCommand.getPropertyId(),
                        unitCommand.getUnitNumber(),
                        unitCommand.getUnitType(),
                        unitCommand.getBeds(),
                        unitCommand.getBaths(),
                        unitCommand.getSize(),
                        unitCommand.getRent(),
                        unitCommand.getDeposit()));
    }

    @Test
    public void createSingleUnitPropertyWithMultipleUnits() {
        AddUnitCommand unitCommand1 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("101")
                .unitType(UnitType.APARTMENT)
                .beds(2)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        AddUnitCommand unitCommand2 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("102")
                .unitType(UnitType.APARTMENT)
                .beds(1)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        CreatePropertyCommand command = CreatePropertyCommand.builder()
                .id("1")
                .name("Test Property")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Anytown")
                        .state("CA")
                        .postalCode("12345")
                        .build())
                .propertyType(PropertyType.SINGLE_UNIT)
                .units(List.of(unitCommand1, unitCommand2))
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectNoEvents()
                .expectException(SingleUnitPropertyException.class);
    }

    @Test
    public void createMultiUnitPropertyWithValidRequest() {
        AddUnitCommand unitCommand1 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("101")
                .unitType(UnitType.APARTMENT)
                .beds(2)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        AddUnitCommand unitCommand2 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("102")
                .unitType(UnitType.APARTMENT)
                .beds(1)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        CreatePropertyCommand command = CreatePropertyCommand.builder()
                .id("1")
                .name("Test Property")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Anytown")
                        .state("CA")
                        .postalCode("12345")
                        .build())
                .propertyType(PropertyType.MULTI_UNIT)
                .units(List.of(unitCommand1, unitCommand2))
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PropertyCreatedEvent(
                        command.getId(),
                        command.getName(),
                        command.getAddress(),
                        command.getPropertyType(),
                        PropertyStatus.ACTIVE
                ), new UnitAddedEvent(
                        unitCommand1.getId(),
                        unitCommand1.getPropertyId(),
                        unitCommand1.getUnitNumber(),
                        unitCommand1.getUnitType(),
                        unitCommand1.getBeds(),
                        unitCommand1.getBaths(),
                        unitCommand1.getSize(),
                        unitCommand1.getRent(),
                        unitCommand1.getDeposit())
                        ,
                        new UnitAddedEvent(
                                unitCommand2.getId(),
                                unitCommand2.getPropertyId(),
                                unitCommand2.getUnitNumber(),
                                unitCommand2.getUnitType(),
                                unitCommand2.getBeds(),
                                unitCommand2.getBaths(),
                                unitCommand2.getSize(),
                                unitCommand2.getRent(),
                                unitCommand2.getDeposit())
                );
    }

    @Test
    public void addUnitToMultiUnitProperty() {
        AddUnitCommand unitCommand1 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("101")
                .unitType(UnitType.APARTMENT)
                .beds(2)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        CreatePropertyCommand createPropertyCommand = CreatePropertyCommand.builder()
                .id("1")
                .name("Test Property")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Anytown")
                        .state("CA")
                        .postalCode("12345")
                        .build())
                .propertyType(PropertyType.MULTI_UNIT)
                .units(List.of(unitCommand1))
                .build();

        AddUnitCommand unitCommand2 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("102")
                .unitType(UnitType.APARTMENT)
                .beds(1)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        fixture.givenCommands(createPropertyCommand)
                .when(unitCommand2)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new UnitAddedEvent(
                unitCommand2.getId(),
                unitCommand2.getPropertyId(),
                unitCommand2.getUnitNumber(),
                unitCommand2.getUnitType(),
                unitCommand2.getBeds(),
                unitCommand2.getBaths(),
                unitCommand2.getSize(),
                unitCommand2.getRent(),
                unitCommand2.getDeposit()));
    }

    @Test
    public void addUnitToSingleUnitProperty() {
        AddUnitCommand unitCommand1 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("101")
                .unitType(UnitType.APARTMENT)
                .beds(2)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        CreatePropertyCommand createPropertyCommand = CreatePropertyCommand.builder()
                .id("1")
                .name("Test Property")
                .address(Address.builder()
                        .street("123 Main St")
                        .city("Anytown")
                        .state("CA")
                        .postalCode("12345")
                        .build())
                .propertyType(PropertyType.SINGLE_UNIT)
                .units(List.of(unitCommand1))
                .build();

        AddUnitCommand unitCommand2 = AddUnitCommand.builder()
                .propertyId("1")
                .unitNumber("102")
                .unitType(UnitType.APARTMENT)
                .beds(1)
                .baths(1)
                .size(1000D)
                .rent(2000D)
                .deposit(1000D)
                .build();

        fixture.givenCommands(createPropertyCommand)
                .when(unitCommand2)
                .expectNoEvents()
                .expectException(SingleUnitPropertyException.class);
    }

}
