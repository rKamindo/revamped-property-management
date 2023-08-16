package com.kamindo.propertymanagement.property;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {
    private final PropertyCommandHandler propertyCommandHandler;
    private final PropertyQueryHandler queryHandler;


    public PropertyController(PropertyCommandHandler propertyCommandHandler, PropertyQueryHandler queryHandler) {
        this.propertyCommandHandler = propertyCommandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public ResponseEntity<?> addProperty(@RequestBody CreatePropertyCommand command) {
        propertyCommandHandler.handle(command);
        return ResponseEntity.ok("Property creation request received");
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getProperties(PropertyQuery propertyQuery) {
        List<PropertyDTO> properties = queryHandler.handle(propertyQuery);
        return ResponseEntity.ok(properties);
    }
}
