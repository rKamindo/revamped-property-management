package com.kamindo.propertymanagement.property;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PropertyCommandHandler {
    private PropertyRepository propertyRepository;
    private ApplicationEventPublisher eventPublisher;

    public PropertyCommandHandler(PropertyRepository propertyRepository, ApplicationEventPublisher eventPublisher) {
        this.propertyRepository = propertyRepository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(CreatePropertyCommand command) {
        Property property = Property.builder()
                .name(command.getName())
                .address(command.getAddress())
                .build();

        propertyRepository.save(property);
        eventPublisher.publishEvent(new PropertyCreatedEvent(property));
    }
}