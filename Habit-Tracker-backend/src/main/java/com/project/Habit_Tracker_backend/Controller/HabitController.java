package com.project.Habit_Tracker_backend.Controller;

import com.project.Habit_Tracker_backend.Entity.Habit;
import com.project.Habit_Tracker_backend.Service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "http://localhost:5173")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping("/add/{userId}")
    public String addHabit(@PathVariable Long userId, @RequestBody Habit habit) {
        return habitService.addHabit(userId, habit.getName());
    }

    @GetMapping("/{userId}")
    public List<Habit> getHabits(@PathVariable Long userId) {
        return habitService.getAllHabits(userId);
    }

    @PutMapping("/mark-done/{habitId}")
    public String markDone(@PathVariable Long habitId) {
        return habitService.markHabitDone(habitId);
    }

    @DeleteMapping("/{habitId}")
    public String deleteHabit(@PathVariable Long habitId) {
        return habitService.deleteHabit(habitId);
    }
}
