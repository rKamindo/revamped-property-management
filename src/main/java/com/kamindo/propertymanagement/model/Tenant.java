package com.kamindo.propertymanagement.model;

import com.kamindo.propertymanagement.command.CreateTenantCommand;
import com.kamindo.propertymanagement.event.TenantCreatedEvent;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
@Builder
@Getter
@Setter
@ToString
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {

    @Id
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Embedded
    private Address address;
    private String email;

    private String phone;


    @CommandHandler
    public Tenant(CreateTenantCommand command) {
        Assert.notNull(command.getFirstName(), "Property id should not be null");
        Assert.notNull(command.getLastName(), "Name should not be null");
        apply(new TenantCreatedEvent(command.getId(), command.getFirstName(),
                command.getLastName(), command.getDateOfBirth(), command.getAddress(), command.getEmail(), command.getPhone()));
    }

    @EventHandler
    public void on(TenantCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.dateOfBirth = event.getDateOfBirth();
        this.address = event.getAddress();
        this.email = event.getEmail();
        this.phone = event.getPhone();
    }
}
