package com.kamindo.propertymanagement.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantMovedInUnit {
    private Long propertyId;
    private Long unitId;
    private Long tenantId;
}
