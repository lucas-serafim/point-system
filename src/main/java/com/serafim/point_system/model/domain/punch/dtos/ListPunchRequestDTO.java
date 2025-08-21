package com.serafim.point_system.model.domain.punch.dtos;

import java.util.Optional;
import java.util.UUID;

public record ListPunchRequestDTO(
        Optional<UUID> employeeId,
        Optional<String> startDate,
        Optional<String> endDate
) {
}
