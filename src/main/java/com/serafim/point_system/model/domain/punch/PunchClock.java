package com.serafim.point_system.model.domain.punch;

import com.serafim.point_system.model.domain.users.User;
import com.serafim.point_system.model.enums.PunchClockTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "punch_clock")
public class PunchClock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private PunchClockTypeEnum type;
    private LocalDateTime timestamp;

    public PunchClock(User user, PunchClockTypeEnum type, LocalDateTime timestamp) {
        this.user = user;
        this.type = type;
        this.timestamp = timestamp;
    }
}
