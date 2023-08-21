package com.kamindo.propertymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tenant {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;
}
