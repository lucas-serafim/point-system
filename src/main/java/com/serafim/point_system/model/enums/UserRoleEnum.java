package com.serafim.point_system.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    EMPLOYEE("employee"),
    ADMIN("admin");

    private final String role;
}
