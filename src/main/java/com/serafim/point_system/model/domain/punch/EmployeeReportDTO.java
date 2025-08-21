package com.serafim.point_system.model.domain.punch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class EmployeeReportDTO {

    @JsonProperty("total_hours")
    private Integer totalHours = 0;

    private final List<EmployeeHoursDTO> employees;

    public EmployeeReportDTO(List<EmployeeHoursDTO> employees) {
        this.employees = employees;
        this.calculateHours();
    }

    private void calculateHours() {
        this.totalHours = this.employees.stream().mapToInt(value -> Math.toIntExact(value.getHoursWorked())).sum();
    }
}
