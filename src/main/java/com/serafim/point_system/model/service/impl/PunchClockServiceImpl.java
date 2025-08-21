package com.serafim.point_system.model.service.impl;

import com.serafim.point_system.model.domain.punch.*;
import com.serafim.point_system.model.domain.punch.dtos.ListPunchRequestDTO;
import com.serafim.point_system.model.domain.punch.dtos.PunchClockRequestDTO;
import com.serafim.point_system.model.domain.punch.dtos.PunchClockResponseDTO;
import com.serafim.point_system.model.domain.punch.dtos.ReportsRequestDTO;
import com.serafim.point_system.model.domain.users.User;
import com.serafim.point_system.model.repository.PunchClockRepository;
import com.serafim.point_system.model.repository.UserRepository;
import com.serafim.point_system.model.service.PunchClockService;
import com.serafim.point_system.model.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PunchClockServiceImpl implements PunchClockService {

    @Autowired
    private PunchClockRepository punchClockRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<EmployeeWorkDayDTO> listPunchRegisters(ListPunchRequestDTO dto) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date = LocalDate.now();

        LocalDate start = dto.startDate().map(startDate -> LocalDate.parse(startDate, dateTimeFormatter)).orElse(date.minusDays(7));
        LocalDate end = dto.endDate().map(endDate -> LocalDate.parse(endDate, dateTimeFormatter)).orElse(date);

        List<User> userList = dto.employeeId()
                .map(id -> this.userRepository.findById(id).stream().toList())
                .orElse(this.userRepository.findAll());

        List<EmployeeWorkDayDTO> dtoList = new ArrayList<>();

        for (User user : userList) {
            Map<LocalDate, List<PunchClock>> grouped = this.listAndGroupPunches(Optional.of(user.getId()));
            List<WorkDayDTO> workDays = this.toWorkDayListDTO(grouped);

            workDays.stream()
                    .filter(workDay -> workDay.getDate().isAfter(start) && workDay.getDate().isBefore(end))
                    .map(workDay -> new EmployeeWorkDayDTO(
                            user.getName(),
                            workDay.getDate(),
                            workDay.getCheckIn(),
                            workDay.getCheckOut(),
                            workDay.getWorkedHours()
                    )).forEach(dtoList::add);
        }

        return dtoList.stream().sorted().toList();
    }

    public List<WorkDayDTO> punchHistory() {
        User user = AuthUtil.getCurrentUser();

        assert user != null;
        Map<LocalDate, List<PunchClock>> grouped = this.listAndGroupPunches(Optional.of(user.getId()));

        return this.toWorkDayListDTO(grouped);
    }

    public EmployeeReportDTO reports(ReportsRequestDTO dto) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date = LocalDate.now();

        LocalDate start = dto.startDate().map(startDate -> LocalDate.parse(startDate, dateTimeFormatter)).orElse(date.minusDays(7));
        LocalDate end = dto.endDate().map(endDate -> LocalDate.parse(endDate, dateTimeFormatter)).orElse(date);

        List<User> users = this.userRepository.findAll();

        List<EmployeeHoursDTO> employeeHours = new ArrayList<>();

        for (User user : users) {
            Map<LocalDate, List<PunchClock>> grouped = this.listAndGroupPunches(Optional.of(user.getId()));

            long workedHours = grouped.entrySet().stream()
                    .filter(key -> key.getKey().isAfter(start) && key.getKey().isBefore(end))
                    .mapToLong(current -> {
                        List<PunchClock> dayPunches = current.getValue();

                        LocalTime checkIn = dayPunches.getFirst().getTimestamp().toLocalTime();
                        LocalTime checkOut = dayPunches.getLast().getTimestamp().toLocalTime();

                        return Duration.between(checkIn, checkOut).toHours();
                    }).sum();

            employeeHours.add(new EmployeeHoursDTO(user.getName(), workedHours));
        }

        return new EmployeeReportDTO(employeeHours);
    }

    private Map<LocalDate, List<PunchClock>> listAndGroupPunches(Optional<UUID> employeeId) {
        List<PunchClock> punches = employeeId.map(this.punchClockRepository::findAllByUserId).orElse(this.punchClockRepository.findAll());
        return punches.stream().collect(Collectors.groupingBy(current -> current.getTimestamp().toLocalDate()));
    }

    private List<WorkDayDTO> toWorkDayListDTO(Map<LocalDate, List<PunchClock>> group) {
        return group.entrySet().stream().map(current -> {
            LocalDate date = current.getKey();
            List<PunchClock> dayPunches = current.getValue();

            LocalTime checkIn = dayPunches.getFirst().getTimestamp().toLocalTime();
            LocalTime checkOut = dayPunches.getLast().getTimestamp().toLocalTime();

            long workedHours = Duration.between(checkIn, checkOut).toHours();

            return new WorkDayDTO(date, checkIn, checkOut, workedHours);
        }).collect(Collectors.toList());
    }
}
