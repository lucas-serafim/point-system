package com.serafim.point_system.controller;

import com.serafim.point_system.model.domain.users.UserRequestDTO;
import com.serafim.point_system.model.domain.users.UserResponseDTO;
import com.serafim.point_system.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody() UserRequestDTO requestDTO) {
        UserResponseDTO responseDTO = this.userService.register(requestDTO);

        if (responseDTO == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
