package com.project.Habit_Tracker_backend.Service;

import com.project.Habit_Tracker_backend.Entity.Habit;
import com.project.Habit_Tracker_backend.Entity.User;
import com.project.Habit_Tracker_backend.Repo.HabitRepo;
import com.project.Habit_Tracker_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    private HabitRepo habitRepository;

    @Autowired
    private UserRepo userRepository;

    public String addHabit(Long userId, String habitName) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return "User not found!";

        Habit habit = new Habit(habitName, userOpt.get());
        habitRepository.save(habit);
        return "Habit added successfully!";
    }

    public List<Habit> getAllHabits(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.map(habitRepository::findByUser).orElse(null);
    }

    public String markHabitDone(Long habitId) {
        Optional<Habit> habitOpt = habitRepository.findById(habitId);
        if (habitOpt.isEmpty()) return "Habit not found!";

        Habit habit = habitOpt.get();
        LocalDate today = LocalDate.now();

        if (habit.getLastCompletedDate() != null) {
            long daysBetween = ChronoUnit.DAYS.between(habit.getLastCompletedDate(), today);

            if (daysBetween == 0) {
                return "Already completed today!";
            } else if (daysBetween == 1) {
                habit.setStreak(habit.getStreak() + 1);
                habit.setStreakBroken(false);
            } else {
                habit.setStreak(1);
                habit.setStreakBroken(true);
            }
        } else {
            habit.setStreak(1);
            habit.setStreakBroken(false);
        }

        habit.setLastCompletedDate(today);
        habitRepository.save(habit);

        if (habit.isStreakBroken()) {
            return "😢 You broke your streak! Starting again from 1.";
        } else {
            return "🔥 Streak updated! Current streak: " + habit.getStreak();
        }
    }

    // Optional delete
    public String deleteHabit(Long habitId) {
        if (!habitRepository.existsById(habitId)) {
            return "Habit not found!";
        }
        habitRepository.deleteById(habitId);
        return "Habit deleted!";
    }
}
