package com.kamindo.propertymanagement.property;

import org.springframework.context.ApplicationEvent;

public class PropertyCreatedEvent extends ApplicationEvent {
    public PropertyCreatedEvent(Object source) {
        super(source);
    }
}
