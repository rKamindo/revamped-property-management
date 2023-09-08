package com.kamindo.propertymanagement.event;

import com.kamindo.propertymanagement.model.Address;
import lombok.Value;

import java.time.LocalDate;


@Value
public class TenantCreatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Address address;
    private String email;
    private String phone;
}
