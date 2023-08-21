package com.kamindo.propertymanagement.repository;

import com.kamindo.propertymanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
