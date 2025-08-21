package com.serafim.point_system.model.domain.users.dtos;

public record UserRequestDTO(
        String name,
        String email,
        String password
) {
}
