package com.kamindo.propertymanagement.controller;

import com.kamindo.propertymanagement.command.CreateTenantCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> createTenant(@RequestBody CreateTenantCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        commandGateway.send(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
