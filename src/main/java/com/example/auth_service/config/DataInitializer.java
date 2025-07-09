package com.example.auth_service.config;

import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Créer des utilisateurs de test s'ils n'existent pas déjà
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // Note: En production, utilisez BCrypt
            admin.setEmail("admin@example.com");
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
        
        if (!userRepository.existsByUsername("user1")) {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("password123");
            user1.setEmail("user1@example.com");
            user1.setRole("USER");
            userRepository.save(user1);
        }
        
        if (!userRepository.existsByUsername("user2")) {
            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword("password123");
            user2.setEmail("user2@example.com");
            user2.setRole("USER");
            userRepository.save(user2);
        }
    }
}
