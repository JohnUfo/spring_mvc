package com.web.service;

import com.web.dto.RegistrationDto;
import com.web.entity.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.ui.Model;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(@NotEmpty(message = "Email should not be empty")String email);

    User findByUsername(@NotEmpty(message = "Username should not be empty") String username);

    void userForFrontEnd(Model model);
}
