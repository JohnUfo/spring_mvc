package com.web.service;

import com.web.dto.RegistrationDto;
import com.web.entity.User;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(@NotEmpty(message = "Email should not be empty")String email);

    User findByUsername(@NotEmpty(message = "Username should not be empty") String username);
}
