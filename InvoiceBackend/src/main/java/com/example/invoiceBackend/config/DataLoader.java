package com.example.invoiceBackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.invoiceBackend.entity.User;
import com.example.invoiceBackend.repository.UserRepository;

@Component
public class DataLoader
        implements CommandLineRunner {

    private final UserRepository
            userRepository;

    public DataLoader(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    @Override
    public void run(
            String... args) {

        if (userRepository.count()
                == 0) {

            userRepository.save(
                    new User(
                            "user1",
                            "password123",
                            "ACTIVE"));

            userRepository.save(
                    new User(
                            "user2",
                            "password123",
                            "ACTIVE"));
        }
    }
}