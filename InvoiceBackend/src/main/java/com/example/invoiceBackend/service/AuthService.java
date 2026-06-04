package com.example.invoiceBackend.service;

import org.springframework.stereotype.Service;

import com.example.invoiceBackend.entity.User;
import com.example.invoiceBackend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    public String login(
            String username,
            String password) {

        User user =
                userRepository
                        .findByUsername(
                                username)
                        .orElse(null);

        if (user == null) {

            return "Invalid User";
        }

        if (!user
                .getPassword()
                .equals(password)) {

            return "Invalid Password";
        }

        if (!"ACTIVE".equalsIgnoreCase(
                user.getAccountStatus())) {

            return "User Inactive";
        }

        return "Login Success";
    }
}