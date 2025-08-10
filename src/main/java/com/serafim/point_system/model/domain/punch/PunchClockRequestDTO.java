package com.serafim.point_system.model.domain.punch;

import com.serafim.point_system.model.enums.PunchClockTypeEnum;

public record PunchClockRequestDTO(
        PunchClockTypeEnum type
) {
}
