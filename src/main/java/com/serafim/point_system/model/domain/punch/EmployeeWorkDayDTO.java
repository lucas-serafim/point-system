package com.serafim.point_system.model.domain.punch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWorkDayDTO implements Comparable<EmployeeWorkDayDTO> {
    private String employee;

    private LocalDate date;

    @JsonProperty("check-in")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkIn;

    @JsonProperty("check-out")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;

    @JsonProperty("worked-hours")
    private Long workedHours;

    @Override
    public int compareTo(EmployeeWorkDayDTO o) {
        return getDate().compareTo(o.getDate());
    }
}
