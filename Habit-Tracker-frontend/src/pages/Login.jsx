import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import API from "../api";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await API.post("/users/login", { email, password });
      setMsg(res.data);
      if (res.data.includes("successful")) {
        localStorage.setItem("userEmail", email);
        navigate("/dashboard");
      }
    } catch {
      setMsg("Error logging in");
    }
  };

  return (
    <div className="login-page">
      <div className="container">
        <h2>Login</h2>
        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          placeholder="Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onClick={handleLogin}>Login</button>
        <p>
          Don’t have an account?{" "}
          <Link to="/register" className="link">
            Register
          </Link>
        </p>
        {msg && <p className="message">{msg}</p>}
      </div>
    </div>
  );
}
