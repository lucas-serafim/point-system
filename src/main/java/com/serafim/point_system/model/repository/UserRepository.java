package com.serafim.point_system.model.repository;

import com.serafim.point_system.model.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
