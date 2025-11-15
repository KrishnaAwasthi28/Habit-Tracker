import { Link, useNavigate } from "react-router-dom";
import { PiClockCountdownFill } from "react-icons/pi";

export default function Navbar() {
  const navigate = useNavigate();
  const isLoggedIn = !!localStorage.getItem("userEmail");

  const handleLogout = () => {
    localStorage.removeItem("userEmail");
    navigate("/");
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <div className="logo">
          <PiClockCountdownFill id="logo" />
        <h2
          className="app-name"
          style={{ cursor: "pointer" }}
          onClick={() => navigate("/")}
        >
          StreakUp
        </h2>
        </div>
      </div>

      <div className="navbar-right">
        <Link to="/about" className="nav-link">
          About
        </Link>
        <a
          href="https://github.com/your-github-username"
          target="_blank"
          rel="noopener noreferrer"
          className="nav-link"
        >
          GitHub
        </a>

        {isLoggedIn ? (
          <button onClick={handleLogout} className="logout-btn">
            Logout
          </button>
        ) : (
          <>
            <Link to="/login" className="nav-link">
              Login
            </Link>
            <Link to="/register" className="nav-link">
              Register
            </Link>
          </>
        )}
      </div>
    </nav>
  );
}
