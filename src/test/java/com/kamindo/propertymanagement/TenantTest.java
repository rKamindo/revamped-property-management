package com.kamindo.propertymanagement;

import com.kamindo.propertymanagement.command.CreateTenantCommand;
import com.kamindo.propertymanagement.event.TenantCreatedEvent;
import com.kamindo.propertymanagement.model.Address;
import com.kamindo.propertymanagement.model.Tenant;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TenantTest {

    private AggregateTestFixture<Tenant> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Tenant.class);
    }

    @Test
    public void createTenantWithValidRequest() {
        String tenantId = UUID.randomUUID().toString();
        Address address = Address.builder()
                .street("123 Main St")
                .city("Anytown")
                .state("CA")
                .postalCode("12345")
                .build();

        CreateTenantCommand command = CreateTenantCommand
                .builder()
                .id(tenantId)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .address(address)
                .email("johndoe@gmail.com")
                .phone("1112223333")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(new TenantCreatedEvent(
                        tenantId,
                        "John",
                        "Doe",
                        LocalDate.of(2000,1,1),
                        address,
                        "johndoe@gmail.com",
                        "1112223333"));
    }
}
