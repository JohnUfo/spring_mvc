package com.web.service.impl;


import com.web.dto.RegistrationDto;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.repository.RoleRepository;
import com.web.repository.UserRepository;
import com.web.security.SecurityUtil;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void userForFrontEnd(Model model) {
        User user = new User();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = findByUsername(username);
        }
        model.addAttribute("user", user);
    }
}
