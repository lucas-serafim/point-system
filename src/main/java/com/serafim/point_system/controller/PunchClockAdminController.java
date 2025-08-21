package com.serafim.point_system.controller;

import com.serafim.point_system.model.domain.punch.EmployeeReportDTO;
import com.serafim.point_system.model.domain.punch.EmployeeWorkDayDTO;
import com.serafim.point_system.model.domain.punch.dtos.ListPunchRequestDTO;
import com.serafim.point_system.model.domain.punch.dtos.ReportsRequestDTO;
import com.serafim.point_system.model.service.PunchClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class PunchClockAdminController {

    @Autowired
    private PunchClockService punchClockService;

    @GetMapping("/punch-clock")
    public ResponseEntity<List<EmployeeWorkDayDTO>> listRegisters(
            @RequestParam(value = "employeeId", required = false) UUID employeeId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {
        List<EmployeeWorkDayDTO> dtoList = this.punchClockService.listPunchRegisters(new ListPunchRequestDTO(
                Optional.ofNullable(employeeId),
                Optional.ofNullable(startDate),
                Optional.ofNullable(endDate)
        ));
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/reports")
    public ResponseEntity<EmployeeReportDTO> reports(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {
        EmployeeReportDTO employeeReportDTO = this.punchClockService.reports(new ReportsRequestDTO(
                Optional.ofNullable(startDate),
                Optional.ofNullable(endDate)
        ));
        return ResponseEntity.ok(employeeReportDTO);
    }
}
