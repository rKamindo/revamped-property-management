package com.kamindo.propertymanagement.unit;

import com.kamindo.propertymanagement.property.Property;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyUnit {

    @Id
    @GeneratedValue
    private Long id;

    private String unitNumber;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
