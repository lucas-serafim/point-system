package com.serafim.point_system.model.domain.users.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

        @NotBlank(message = "Email is required.")
        @Email(message = "Email should be valid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
        String email,

        @NotBlank(message = "Password is required.")
        String password
) {
}
