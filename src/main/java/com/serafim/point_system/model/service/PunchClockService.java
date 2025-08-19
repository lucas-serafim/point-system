package com.serafim.point_system.model.service;

import com.serafim.point_system.model.domain.punch.*;

import java.util.List;
import java.util.Optional;

public interface PunchClockService {
    PunchClockResponseDTO register(PunchClockRequestDTO dto);

    List<WorkDayDTO> punchHistory();

    List<EmployeeWorkDayDTO> listPunchRegisters(ListPunchRequestDTO dto);
}
