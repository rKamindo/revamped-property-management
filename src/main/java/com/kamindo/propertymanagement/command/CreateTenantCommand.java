package com.kamindo.propertymanagement.command;

import com.kamindo.propertymanagement.model.Address;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateTenantCommand {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private Address address;
    private String phone;
}
