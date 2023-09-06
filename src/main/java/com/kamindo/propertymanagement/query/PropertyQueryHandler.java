package com.kamindo.propertymanagement.query;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyQueryHandler {

    private final PropertyRepository propertyRepository;

    @QueryHandler
    public List<PropertyDTO> handle(AllProperties query) {
        return propertyRepository.findProperties();
    }
}
