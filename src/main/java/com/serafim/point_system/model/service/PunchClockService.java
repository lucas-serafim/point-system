package com.serafim.point_system.model.service;

import com.serafim.point_system.model.domain.punch.*;
import com.serafim.point_system.model.domain.punch.dtos.ListPunchRequestDTO;
import com.serafim.point_system.model.domain.punch.dtos.PunchClockRequestDTO;
import com.serafim.point_system.model.domain.punch.dtos.PunchClockResponseDTO;
import com.serafim.point_system.model.domain.punch.dtos.ReportsRequestDTO;

import java.util.List;

public interface PunchClockService {
    PunchClockResponseDTO register(PunchClockRequestDTO dto);

    List<WorkDayDTO> punchHistory();

    List<EmployeeWorkDayDTO> listPunchRegisters(ListPunchRequestDTO dto);

    EmployeeReportDTO reports(ReportsRequestDTO dto);
}
