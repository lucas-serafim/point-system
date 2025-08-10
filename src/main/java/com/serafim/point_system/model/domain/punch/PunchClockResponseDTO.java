package com.serafim.point_system.model.domain.punch;

import java.time.Instant;
import java.time.LocalDateTime;

public record PunchClockResponseDTO(
        String message,
        LocalDateTime timestamp
) {
}
