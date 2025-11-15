import { useEffect, useState } from "react";
import API from "../api";
import HabitCard from "../components/HabitCard";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const [habits, setHabits] = useState([]);
  const [newHabit, setNewHabit] = useState("");
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const email = localStorage.getItem("userEmail");
    if (!email) navigate("/login");
    else fetchUser(email);
  }, []);

  const fetchUser = async (email) => {
    const res = await API.get(`/users/getByEmail/${email}`);
    setUser(res.data);
    fetchHabits(res.data.id);
  };

  const fetchHabits = async (userId) => {
    const res = await API.get(`/habits/${userId}`);
    setHabits(res.data);
  };

  const addHabit = async () => {
    if (!newHabit.trim() || !user) return;
    await API.post(`/habits/add/${user.id}`, { name: newHabit });
    setNewHabit("");
    fetchHabits(user.id);
  };

  const markDone = async (id) => {
    const res = await API.put(`/habits/mark-done/${id}`);
    alert(res.data);
    fetchHabits(user.id);
  };

  return (
    <div className="dashboard">
      <h1>Welcome, {user?.username} 👋</h1>
      <p className="quote">Every streak starts with a single click. Yes, this one too</p>
      <div className="add-habit">
        <input
          placeholder="Add new habit..."
          value={newHabit}
          onChange={(e) => setNewHabit(e.target.value)}
        />
        <button onClick={addHabit}>Add</button>
      </div>

      <div className="habit-list">
        {habits.length ? (
          habits.map((h) => (
            <HabitCard key={h.id} habit={h} onMarkDone={markDone} />
          ))
        ) : (
          <p>No habits yet 😅</p>
        )}
      </div>
    </div>
  );
}
