package com.kamindo.propertymanagement.model;

import com.kamindo.propertymanagement.command.AddUnitCommand;
import com.kamindo.propertymanagement.command.CreatePropertyCommand;
import com.kamindo.propertymanagement.event.PropertyCreatedEvent;
import com.kamindo.propertymanagement.event.UnitAddedEvent;
import com.kamindo.propertymanagement.exception.SingleUnitPropertyException;
import jakarta.persistence.*;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.Hibernate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
@Getter
@Setter
@ToString
@Builder
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @AggregateIdentifier
    private String id;
    private String name;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @AggregateMember
    @Builder.Default
    private List<Unit> units = new ArrayList<>();
    @CommandHandler
    public Property(CreatePropertyCommand cmd) {
        Assert.notNull(cmd.getId(), "Property id should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");
        Assert.notNull(cmd.getUnits(), "Property units should not be null");
        Assert.notEmpty(cmd.getUnits(), "A property should have at least 1 unit");

        if (cmd.getPropertyType() == PropertyType.SINGLE_UNIT && cmd.getUnits().size() > 1) {
            throw new SingleUnitPropertyException("A single unit property can only have one unit");
        }

        PropertyCreatedEvent propertyCreatedEvent = new PropertyCreatedEvent(cmd.getId(), cmd.getName(), cmd.getAddress(), cmd.getPropertyType(), PropertyStatus.ACTIVE);
        apply(propertyCreatedEvent);

        for (AddUnitCommand unitCommand : cmd.getUnits()) {
            unitCommand.setId(UUID.randomUUID().toString());
            apply(new UnitAddedEvent(
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
    }

    @CommandHandler
    public void handle(AddUnitCommand cmd) {
        Assert.notNull(cmd.getPropertyId(), "Property id should not be null");
        Assert.notNull(cmd.getUnitNumber(), "Unit number should not be null");

        if (this.propertyType == PropertyType.SINGLE_UNIT) {
            throw new SingleUnitPropertyException("A single unit property can only have one unit");
        }

        apply(new UnitAddedEvent(
                cmd.getId(),
                cmd.getPropertyId(),
                cmd.getUnitNumber(),
                cmd.getUnitType(),
                cmd.getBeds(),
                cmd.getBaths(),
                cmd.getSize(),
                cmd.getRent(),
                cmd.getDeposit()));
    }

    @EventHandler
    public void on(PropertyCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.address = event.getAddress();
        this.propertyType = event.getPropertyType();
        this.propertyStatus = event.getPropertyStatus();
    }

    @EventHandler
    public void on(UnitAddedEvent event) {
        if (units == null) {
            units = new ArrayList<>();
        }

        units.add(new Unit(
                event.getId(),
                event.getPropertyId(),
                event.getUnitNumber(),
                event.getUnitType(),
                event.getBeds(),
                event.getBaths(),
                event.getSize(),
                event.getRent(),
                event.getDeposit()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Property property = (Property) o;
        return id != null && Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}