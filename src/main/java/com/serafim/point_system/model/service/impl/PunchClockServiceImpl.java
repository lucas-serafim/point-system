package com.serafim.point_system.model.service.impl;

import com.serafim.point_system.model.domain.punch.PunchClock;
import com.serafim.point_system.model.domain.punch.PunchClockRequestDTO;
import com.serafim.point_system.model.domain.punch.PunchClockResponseDTO;
import com.serafim.point_system.model.domain.punch.WorkDayDTO;
import com.serafim.point_system.model.domain.users.User;
import com.serafim.point_system.model.repository.PunchClockRepository;
import com.serafim.point_system.model.service.PunchClockService;
import com.serafim.point_system.model.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PunchClockServiceImpl implements PunchClockService {

    @Autowired
    private PunchClockRepository punchClockRepository;

    @Override
    public PunchClockResponseDTO register(PunchClockRequestDTO dto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = AuthUtil.getCurrentUser();

        PunchClock punchClock = new PunchClock(
                user,
                dto.type(),
                localDateTime
        );

        this.punchClockRepository.save(punchClock);

        return new PunchClockResponseDTO(
                "Ponto registrado com sucesso",
                localDateTime
        );
    }

    public List<WorkDayDTO> punchHistory() {
        User user = AuthUtil.getCurrentUser();
        List<PunchClock> punches = this.punchClockRepository.findAllByUserId(user.getId());

        Map<LocalDate, List<PunchClock>> grouped = punches.stream().collect(Collectors.groupingBy(current -> current.getTimestamp().toLocalDate()));

        return grouped.entrySet().stream().map(current -> {
            LocalDate date = current.getKey();
            List<PunchClock> dayPunches = current.getValue();

            LocalTime checkIn = dayPunches.getFirst().getTimestamp().toLocalTime();
            LocalTime checkOut = dayPunches.getLast().getTimestamp().toLocalTime();

            long workedHours = Duration.between(checkIn, checkOut).toHours();

            return new WorkDayDTO(date, checkIn, checkOut, workedHours);
        }).collect(Collectors.toList());
    }
}
