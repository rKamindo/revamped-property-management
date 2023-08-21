package com.kamindo.propertymanagement.controller;

import com.kamindo.propertymanagement.command.CreateTenantCommand;
import com.kamindo.propertymanagement.model.Tenant;
import com.kamindo.propertymanagement.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantRepository tenantRepository;

    @PostMapping
    public ResponseEntity<?> createTenant(@RequestBody CreateTenantCommand command) {
        Tenant tenant = Tenant
                .builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(command.getAddress())
                .email(command.getEmail())
                .phone(command.getPhone())
                .build();

        Tenant newTenant = tenantRepository.save(tenant);
        return new ResponseEntity<>(newTenant.getId(), HttpStatus.CREATED);
    }
}
