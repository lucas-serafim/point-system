package com.serafim.point_system.model.domain.punch.dtos;

import com.serafim.point_system.model.enums.PunchClockTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PunchClockRequestDTO(

        @NotBlank(message = "Type is required.")
        PunchClockTypeEnum type
) {
}
