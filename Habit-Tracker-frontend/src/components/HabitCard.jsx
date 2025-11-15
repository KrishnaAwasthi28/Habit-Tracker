
export default function HabitCard({ habit, onMarkDone }) {
  return (
    <div className="habit-card">
      <div>
        <h3>{habit.name}</h3>
        <p>
          🔥 {habit.streak}-day streak{" "}
          {habit.streakBroken && (
            <span className="broken">(Streak Broken)</span>
          )}
        </p>
        {habit.lastCompletedDate && (
          <small>Last Done: {habit.lastCompletedDate}</small>
        )}
      </div>
      {!habit.streakBroken ? (
        <button onClick={() => onMarkDone(habit.id)}>Mark Done</button>
      ) : (
        <span className="sad-msg">😢 You broke your streak!</span>
      )}
    </div>
  );
}
