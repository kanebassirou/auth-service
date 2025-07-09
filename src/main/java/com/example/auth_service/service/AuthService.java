package com.example.auth_service.service;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.LoginResponse;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Note: Dans un vrai projet, vous devriez utiliser BCrypt pour hasher les mots de passe
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return new LoginResponse(true, "Connexion réussie", user.getId(), 
                                       user.getUsername(), user.getRole());
            } else {
                return new LoginResponse(false, "Mot de passe incorrect", null, null, null);
            }
        } else {
            return new LoginResponse(false, "Utilisateur non trouvé", null, null, null);
        }
    }
    
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
