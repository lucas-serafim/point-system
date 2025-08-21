package com.serafim.point_system.model.service;

import com.serafim.point_system.model.domain.users.dtos.login.LoginRequestDTO;
import com.serafim.point_system.model.domain.users.dtos.login.LoginResponseDTO;
import com.serafim.point_system.model.domain.users.dtos.UserRequestDTO;
import com.serafim.point_system.model.domain.users.dtos.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(UserRequestDTO dto);
    LoginResponseDTO login(LoginRequestDTO dto);
}
