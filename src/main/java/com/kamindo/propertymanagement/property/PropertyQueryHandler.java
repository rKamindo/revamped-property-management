package com.kamindo.propertymanagement.property;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyQueryHandler implements QueryHandler<PropertyQuery, List<PropertyDTO>> {
    private final PropertyRepository propertyRepository;

    public PropertyQueryHandler(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<PropertyDTO> handle(PropertyQuery query) {
        List<PropertyDTO> properties =
                propertyRepository
                        .findAll()
                        .stream().map(this::mapToDTO)
                        .collect(Collectors.toList());
        return properties;
    }

    private PropertyDTO mapToDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setName(property.getName());
        dto.setAddress(property.getAddress());
        return dto;
    }
}
