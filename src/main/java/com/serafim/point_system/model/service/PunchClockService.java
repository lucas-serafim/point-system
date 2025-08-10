package com.serafim.point_system.model.service;

import com.serafim.point_system.model.domain.punch.PunchClockRequestDTO;
import com.serafim.point_system.model.domain.punch.PunchClockResponseDTO;
import com.serafim.point_system.model.domain.punch.WorkDayDTO;

import java.util.List;

public interface PunchClockService {
    PunchClockResponseDTO register(PunchClockRequestDTO dto);

    List<WorkDayDTO> punchHistory();
}
