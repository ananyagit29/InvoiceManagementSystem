package com.example.invoiceBackend.service;

import org.springframework.stereotype.Service;

import com.example.invoiceBackend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    public boolean login(
            String username,
            String password) {

        return userRepository
                .findByUsername(username)
                .map(user ->
                        user.getPassword()
                                .equals(password))
                .orElse(false);
    }
}