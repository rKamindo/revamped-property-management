package com.kamindo.propertymanagement;

import jakarta.persistence.*;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
@Getter
@Setter
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
    @JoinColumn(name = "propertyId")
    @AggregateMember
    private List<Unit> units = new ArrayList<>();

    @CommandHandler
    public Property(CreatePropertyCommand cmd) {
        propertyStatus = PropertyStatus.ACTIVE;
        apply(new PropertyCreatedEvent(cmd.getId(), cmd.getName(), cmd.getAddress(), cmd.getPropertyType()));

        if (cmd.getUnits() != null && !cmd.getUnits().isEmpty()) {
            for (AddUnitCommand unitCommand : cmd.getUnits()) {
                if (this.propertyType == PropertyType.SINGLE_UNIT && units.size() >= 1) {
                    throw new RuntimeException("A single-unit property can have only one unit.");
                }
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
    }

    @EventHandler
    public void on(PropertyCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.address = event.getAddress();
        this.propertyType = event.getPropertyType();
    }

    @EventHandler
    public void on(UnitAddedEvent event) {
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

    public void addUnit(Unit unit) {
    }
}