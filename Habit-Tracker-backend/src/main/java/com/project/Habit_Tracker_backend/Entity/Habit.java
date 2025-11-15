package com.project.Habit_Tracker_backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int streak = 0;

    private LocalDate lastCompletedDate;

    private boolean streakBroken = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Habit() {}

    public Habit(String name, User user) {
        this.name = name;
        this.user = user;
        this.streak = 0;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getStreak() { return streak; }
    public void setStreak(int streak) { this.streak = streak; }

    public LocalDate getLastCompletedDate() { return lastCompletedDate; }
    public void setLastCompletedDate(LocalDate lastCompletedDate) { this.lastCompletedDate = lastCompletedDate; }

    public boolean isStreakBroken() { return streakBroken; }
    public void setStreakBroken(boolean streakBroken) { this.streakBroken = streakBroken; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
