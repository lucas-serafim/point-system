package com.serafim.point_system.model.domain.users;

public record UserRequestDTO(
        String name,
        String email,
        String password
) {
}
