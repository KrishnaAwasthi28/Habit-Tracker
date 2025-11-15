package com.project.Habit_Tracker_backend.Service;

import com.project.Habit_Tracker_backend.Entity.User;
import com.project.Habit_Tracker_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public String register(User user) {
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return "Email already registered!";
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(String email, String password) {
        Optional<User> existing = userRepository.findByEmail(email);
        if (existing.isEmpty()) {
            return "No user found with this email!";
        }

        User user = existing.get();
        if (!user.getPassword().equals(password)) {
            return "Invalid password!";
        }

        return "Login successful!";
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
