package com.serafim.point_system.model.domain.users;

public record LoginRequestDTO(
        String email,
        String password
) {
}
