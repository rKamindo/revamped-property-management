package com.kamindo.propertymanagement;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody CreatePropertyCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        commandGateway.send(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
