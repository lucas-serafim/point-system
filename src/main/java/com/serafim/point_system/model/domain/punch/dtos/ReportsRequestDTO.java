package com.serafim.point_system.model.domain.punch.dtos;

import java.util.Optional;

public record ReportsRequestDTO(
        Optional<String> startDate,
        Optional<String> endDate
) {
}
