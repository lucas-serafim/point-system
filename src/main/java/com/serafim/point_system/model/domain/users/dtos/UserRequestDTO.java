package com.serafim.point_system.model.domain.users.dtos;

import jakarta.validation.constraints.*;

public record UserRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email should be valid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
        String email,

        @NotBlank(message = "Password is required.")
        @Size(min = 6, message = "Password must have 6 or more characters.")
        String password
) {
}
