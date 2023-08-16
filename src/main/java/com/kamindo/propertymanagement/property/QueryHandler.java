package com.kamindo.propertymanagement.property;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface QueryHandler<Q, R> {
    R handle(Q query);
}
