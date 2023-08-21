package com.kamindo.propertymanagement.command;

import com.kamindo.propertymanagement.model.Address;
import lombok.Data;

@Data
public class CreateTenantCommand {
    private String firstName;
    private String lastName;
    private Address address;
    private String email;
    private String phone;
}
