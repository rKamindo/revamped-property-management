package com.kamindo.propertymanagement.controller;

import com.kamindo.propertymanagement.command.AddTenantCommand;
import com.kamindo.propertymanagement.command.AddUnitCommand;
import com.kamindo.propertymanagement.commandhandler.PropertyCommandService;
import com.kamindo.propertymanagement.readmodel.PropertyInfo;
import com.kamindo.propertymanagement.repository.PropertyRepository;
import com.kamindo.propertymanagement.command.CreatePropertyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyCommandService propertyCommandService;
    private final PropertyRepository propertyRepository;
    @PostMapping
    public ResponseEntity<?> createProperty(@RequestBody CreatePropertyCommand command) {
        Long propertyId = propertyCommandService.createProperty(command);
        return new ResponseEntity<>(propertyId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropertyInfo>> getProperties() {
        List<PropertyInfo> properties = propertyRepository.getProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping("{propertyId}/units/{unitId}/tenants/{tenantId}")
    public ResponseEntity<?> moveInTenant(@PathVariable Long propertyId,
                                          @PathVariable Long tenantId,
                                          @PathVariable Long unitId) {

        AddTenantCommand command = new AddTenantCommand(propertyId, unitId, tenantId);
        propertyCommandService.addTenant(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
