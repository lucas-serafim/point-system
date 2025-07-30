package com.serafim.point_system.model.domain.users;

import com.serafim.point_system.model.enums.UserRoleEnum;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        UserRoleEnum role
) {
}
