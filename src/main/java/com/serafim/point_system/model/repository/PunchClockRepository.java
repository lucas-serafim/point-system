package com.serafim.point_system.model.repository;

import com.serafim.point_system.model.domain.punch.PunchClock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PunchClockRepository extends JpaRepository<PunchClock, UUID> {
    List<PunchClock> findAllByUserId(UUID userId);
}
