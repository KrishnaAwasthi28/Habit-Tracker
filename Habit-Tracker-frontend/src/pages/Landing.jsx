import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

export default function Landing() {
  const navigate = useNavigate();

  useEffect(() => {
    const user = localStorage.getItem("userEmail");
    if (user) navigate("/dashboard");
  }, []);

  return (
    <div className="landing-page">
      <div className="landing-content">
        <h1>Welcome to Habit Tracker 🌱</h1>
        <p>
          Build better habits, one day at a time.  
          Track your daily goals and stay consistent with streaks 🔥
        </p>
        <img src="healthy-lifestyle.png" alt="" id="hero-img"/>
        <div className="landing-buttons">
          <button onClick={() => navigate("/login")}>Login</button>
          <button onClick={() => navigate("/register")}>Register</button>
        </div>
      </div>
    </div>
  );
}
