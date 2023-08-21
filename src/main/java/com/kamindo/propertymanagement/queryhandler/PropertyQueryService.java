package com.kamindo.propertymanagement.queryhandler;

import com.kamindo.propertymanagement.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyQueryService {
    private final PropertyRepository propertyRepository;

}
