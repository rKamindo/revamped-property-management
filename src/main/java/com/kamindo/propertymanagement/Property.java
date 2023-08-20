package com.kamindo.propertymanagement;

import jakarta.persistence.*;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class Property {
    @Id
    @AggregateIdentifier
    @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Unit> units;

    @CommandHandler
    public Property(CreatePropertyCommand command) {
        PropertyCreatedEvent propertyCreatedEvent = new PropertyCreatedEvent();
        BeanUtils.copyProperties(command, propertyCreatedEvent);
        apply(propertyCreatedEvent);
    }
    public void addUnit(Unit unit) {
        if (units == null) {
            units = new ArrayList<>();
        }

        if (this.propertyType == PropertyType.SINGLE_UNIT && units.size() >= 1) {
            throw new RuntimeException("A single-unit property can have only one unit.");
        }

        units.add(unit);
        unit.setProperty(this);
    }
}