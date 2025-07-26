package com.serafim.point_system.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PunchClockTypeEnum {

    CHECK_IN("check-in"),
    CHECK_OUT("check-out");

    private final String type;
}
