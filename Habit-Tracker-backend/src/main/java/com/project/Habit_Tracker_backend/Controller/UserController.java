package com.project.Habit_Tracker_backend.Controller;

import com.project.Habit_Tracker_backend.Entity.User;
import com.project.Habit_Tracker_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // React app origin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/getByEmail/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).orElse(null);
    }
}
