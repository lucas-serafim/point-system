package com.serafim.point_system.model.domain.users.dtos.login;

public record LoginRequestDTO(
        String email,
        String password
) {
}
