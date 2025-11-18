<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Habit Tracker — README</title>
  </head>

  <body>

<h1 align="center">Habit Tracker — Daily Streaks</h1>

<p align="center">
  <em>A simple Habit Tracker web app with per-user habits and daily streak logic.</em><br/>
  <strong>React.js</strong> (frontend) + <strong>Spring Boot</strong> (backend) + <strong>MySQL</strong>.
</p>

<hr/>

<h2>🚀 Project Overview</h2>
<p>
  Habit Tracker helps users create daily habits, mark them completed each day, and automatically
  track consecutive-day streaks. If a day is missed, the app detects a <strong>broken streak</strong>
  and shows a playful message. Each user has their own set of habits (basic auth).
</p>

<h2>✨ Key Features</h2>
<ul>
  <li>User Authentication (Register / Login — basic auth)</li>
  <li>Add / Delete habits per user</li>
  <li>Mark habit as <strong>Done</strong> for the day</li>
  <li>🔥 Automatic daily streak calculation (continue / reset)</li>
  <li>Broken-streak detection with a friendly message</li>
  <li>Responsive, minimal UI (pure CSS)</li>
  <li>Navbar with About, GitHub and Logout</li>
</ul>

<h2>🧰 Tech Stack</h2>
<ul>
  <li>Frontend: React.js (Vite) + plain CSS</li>
  <li>Backend: Spring Boot + Spring Data JPA</li>
  <li>Database: MySQL</li>
  <li>HTTP client: Axios</li>
</ul>

<hr/>

<h2>📁 Repository Structure (recommended)</h2>
<pre>
habit-tracker/
├── backend/
│   ├── src/main/java/com/habittracker/...
│   ├── pom.xml
│   └── application.properties
└── frontend/
    ├── src/
    │   ├── pages/
    │   ├── components/
    │   └── api.js
    ├── package.json
    └── index.css
</pre>

<hr/>

<h2>⚙️ Backend — Setup & Run</h2>

<h3>1. Create MySQL database</h3>
<pre><code>
CREATE DATABASE habit_tracker;
-- create a user or use root. Make sure credentials match application.properties
</code></pre>

<h3>2. application.properties (example)</h3>
<pre><code>
spring.datasource.url=jdbc:mysql://localhost:3306/habit_tracker?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
</code></pre>

<h3>3. Run backend</h3>
<pre><code>
cd backend
mvn spring-boot:run
</code></pre>

<hr/>

<h2>⚙️ Frontend — Setup & Run</h2>

<h3>1. Install & run</h3>
<pre><code>
cd frontend
npm install
npm run dev
# default: http://localhost:5173
</code></pre>

<h3>2. API base URL (src/api.js)</h3>
<pre><code>
import axios from 'axios';
const API = axios.create({ baseURL: 'http://localhost:8080/api' });
export default API;
</code></pre>

<hr/>

<h2>🔌 API Endpoints</h2>

<h3>Users</h3>
<ul>
  <li><code>POST /api/users/register</code> — Register user. Body: <code>{ username, email, password }</code></li>
  <li><code>POST /api/users/login</code> — Login. Body: <code>{ email, password }</code>. (Returns message)</li>
  <li><code>GET /api/users/getByEmail/{email}</code> — Get user by email (returns user JSON with id).</li>
</ul>

<h3>Habits</h3>
<ul>
  <li><code>POST /api/habits/add/{userId}</code> — Add a new habit for user. Body: <code>{ name }</code></li>
  <li><code>GET /api/habits/{userId}</code> — Get all habits for user</li>
  <li><code>PUT /api/habits/mark-done/{habitId}</code> — Mark habit done (applies streak logic)</li>
  <li><code>DELETE /api/habits/{habitId}</code> — Delete habit</li>
</ul>

<hr/>

<h2>🧠 Streak Logic (summary)</h2>
<p>When a user clicks <em>Mark Done</em>, backend compares <code>lastCompletedDate</code> with today:</p>
<ul>
  <li>If already completed today: return "Already completed today!"</li>
  <li>If lastCompletedDate == yesterday: <code>streak += 1</code></li>
  <li>If lastCompletedDate &lt; yesterday: <code>streak = 1</code> and flag <code>streakBroken = true</code></li>
  <li>If lastCompletedDate == null: <code>streak = 1</code></li>
</ul>

<hr/>

<h2>🧪 Demo Data (SQL)</h2>
<p>Insert demo user and habits (adjust user_id if needed):</p>
<pre><code>
INSERT INTO users (username, email, password) VALUES
('Demo User', 'demo@habit.com', '12345');

-- If your DB supports INTERVAL or DATE_SUB, use the appropriate syntax.
INSERT INTO habits (name, streak, last_completed_date, streak_broken, user_id) VALUES
('Drink Water', 5, DATE_SUB(CURDATE(), INTERVAL 1 DAY), false, 1),
('Read 10 Pages', 1, DATE_SUB(CURDATE(), INTERVAL 3 DAY), true, 1),
('Meditation', 4, CURDATE(), false, 1),
('Workout', 1, DATE_SUB(CURDATE(), INTERVAL 5 DAY), true, 1),
('Wake Up Early', 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), false, 1),
('Clean Room', 0, NULL, false, 1),
('Code for 1 Hour', 3, CURDATE(), false, 1);
</code></pre>

<hr/>

<h2>🎬 How to Record Demo (quick script)</h2>
<ol>
  <li>Open app at <code>http://localhost:5173</code> — Landing page appears.</li>
  <li>Register or login using <code>demo@habit.com / 12345</code>.</li>
  <li>Dashboard: show sample habits (streaks, broken messages).</li>
  <li>Click <em>Mark Done</em> on a habit that was completed yesterday — show streak increment alert.</li>
  <li>Add a new habit and mark it done to show streak starting from 1.</li>
  <li>Show Navbar, About page, and Logout flow.</li>
</ol>

<hr/>

<h2>🖼️ Screenshots</h2>
<p>Replace the image file names with actual screenshots inside your repo (<code>/assets/screenshots/</code>).</p>
<ul>
  <li><code>assets/screenshots/landing.png</code> — Landing page</li>
  <li><code>assets/screenshots/dashboard.png</code> — Dashboard with habits</li>
  <li><code>assets/screenshots/about.png</code> — About page</li>
</ul>

<hr/>

<h2>🔒 CORS Note (development)</h2>
<p>If your frontend is on <code>http://localhost:5173</code> and backend on <code>http://localhost:8080</code>, enable CORS in Spring Boot:</p>
<pre><code>
@Configuration
public class CorsConfig {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*");
      }
    };
  }
}
</code></pre>

<hr/>

<h2>✅ Future Improvements</h2>
<ul>
  <li>Replace basic auth with JWT for secure stateless sessions</li>
  <li>Add calendar view to visualize completion history</li>
  <li>Push notifications or email reminders</li>
  <li>Profiles, categories, habit streak leaderboard</li>
  <li>Dark mode / theming options</li>
</ul>

<hr/>

<h2>🔗 Useful Links</h2>
<ul>
  <li>Frontend: <em>link to your frontend folder / GitHub repo</em></li>
  <li>Backend: <em>link to your backend folder / GitHub repo</em></li>
  <li>Live demo: <em>link (if deployed)</em></li>
</ul>

<hr/>

<hr/>

<p align="center">
  Built with ❤️ by <strong>Krishna Awasthi</strong>  
  <br/>
  <small>Share improvements or issues via the GitHub repo.</small>
</p>

  </body>
</html>
