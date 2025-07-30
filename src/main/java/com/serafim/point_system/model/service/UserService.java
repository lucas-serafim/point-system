package com.serafim.point_system.model.service;

import com.serafim.point_system.model.domain.users.UserRequestDTO;
import com.serafim.point_system.model.domain.users.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDTO register(UserRequestDTO dto);
}
