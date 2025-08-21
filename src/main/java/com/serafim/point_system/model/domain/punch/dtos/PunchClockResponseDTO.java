package com.serafim.point_system.model.domain.punch.dtos;

import java.time.LocalDateTime;

public record PunchClockResponseDTO(
        String message,
        LocalDateTime timestamp
) {
}
