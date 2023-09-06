package com.kamindo.propertymanagement.query;

import com.kamindo.propertymanagement.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, String> {

    @Query("SELECT new com.kamindo.propertymanagement.query.PropertyDTO(" +
            "p.id, p.name, p.address, p.propertyType, p.propertyStatus, " +
            "COUNT(u.id)) " +
            "FROM Property p LEFT JOIN p.units u GROUP BY p.id")
    List<PropertyDTO> findProperties();
}