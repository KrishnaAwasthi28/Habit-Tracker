package com.project.Habit_Tracker_backend.Repo;

import com.project.Habit_Tracker_backend.Entity.Habit;
import com.project.Habit_Tracker_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepo extends JpaRepository<Habit, Long> {
    List<Habit> findByUser(User user);
}