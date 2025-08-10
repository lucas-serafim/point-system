package com.serafim.point_system.model.utils;

import com.serafim.point_system.model.domain.users.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }

        return null;
    }
}
