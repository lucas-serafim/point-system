package com.serafim.point_system.controller;

import com.serafim.point_system.model.domain.punch.PunchClockRequestDTO;
import com.serafim.point_system.model.domain.punch.PunchClockResponseDTO;
import com.serafim.point_system.model.domain.punch.WorkDayDTO;
import com.serafim.point_system.model.service.PunchClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/punch-clock")
public class PunchClockController {

    @Autowired
    private PunchClockService punchClockService;

    @PostMapping
    public ResponseEntity<PunchClockResponseDTO> register(@RequestBody PunchClockRequestDTO requestDTO) {
        PunchClockResponseDTO responseDTO = this.punchClockService.register(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<List<WorkDayDTO>> punchHistory() {
        List<WorkDayDTO> history = this.punchClockService.punchHistory();
        return ResponseEntity.ok(history);
    }
}
