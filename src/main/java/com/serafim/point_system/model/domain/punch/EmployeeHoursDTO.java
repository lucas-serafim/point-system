package com.serafim.point_system.model.domain.punch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHoursDTO {

    private String name;

    @JsonProperty("hours_worked")
    private Long hoursWorked;
}
