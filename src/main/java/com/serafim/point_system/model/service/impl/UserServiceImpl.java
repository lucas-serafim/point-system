package com.serafim.point_system.model.service.impl;

import com.serafim.point_system.model.domain.users.User;
import com.serafim.point_system.model.domain.users.UserRequestDTO;
import com.serafim.point_system.model.domain.users.UserResponseDTO;
import com.serafim.point_system.model.enums.UserRoleEnum;
import com.serafim.point_system.model.repository.UserRepository;
import com.serafim.point_system.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
        // TODO: check email

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User(
                dto.name(),
                dto.email(),
                encryptedPassword,
                UserRoleEnum.EMPLOYEE
        );

        this.userRepository.save(user);

        return this.toResponseDTO(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
