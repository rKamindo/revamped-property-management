package com.kamindo.propertymanagement.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTenantCommand {
    private Long tenantId;
    private Long propertyId;
    private Long unitId;
}
