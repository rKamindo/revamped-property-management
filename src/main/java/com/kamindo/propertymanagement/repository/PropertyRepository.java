package com.kamindo.propertymanagement.repository;

import com.kamindo.propertymanagement.readmodel.PropertyInfo;
import com.kamindo.propertymanagement.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT NEW com.kamindo.propertymanagement.readmodel.PropertyInfo(p.name, p.address, p.propertyType, COUNT(u)) FROM Property p LEFT JOIN p.units u GROUP BY p")
    List<PropertyInfo> getProperties();

}
