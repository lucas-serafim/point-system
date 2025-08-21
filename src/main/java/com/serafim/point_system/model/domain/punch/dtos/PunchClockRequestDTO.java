package com.serafim.point_system.model.domain.punch.dtos;

import com.serafim.point_system.model.enums.PunchClockTypeEnum;

public record PunchClockRequestDTO(
        PunchClockTypeEnum type
) {
}
