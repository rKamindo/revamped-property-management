package com.kamindo.propertymanagement.controller;

import com.kamindo.propertymanagement.exception.SingleUnitPropertyException;
import com.kamindo.propertymanagement.command.AddUnitCommand;
import com.kamindo.propertymanagement.command.CreatePropertyCommand;
import com.kamindo.propertymanagement.query.AllProperties;
import com.kamindo.propertymanagement.query.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public ResponseEntity<String> createProperty(@RequestBody CreatePropertyCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        commandGateway.send(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("{propertyId}/unit")
    public ResponseEntity<String> addUnit(@PathVariable String propertyId, @RequestBody AddUnitCommand command) {
        try {
            String unitId = UUID.randomUUID().toString();
            command.setPropertyId(propertyId);
            command.setId(unitId);
            commandGateway.send(command);
            return new ResponseEntity<>(unitId, HttpStatus.CREATED);
        } catch (AggregateNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Property by id %s not found", propertyId));
        } catch (SingleUnitPropertyException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @GetMapping
    public Future<List<PropertyDTO>> getProperties() {
        return queryGateway.query(new AllProperties(), ResponseTypes.multipleInstancesOf(PropertyDTO.class));

    }
}
