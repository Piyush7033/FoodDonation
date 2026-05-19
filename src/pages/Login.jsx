import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { loginUser } from '../services/authService.js';
import { validateEmail } from '../utils/validators.js';
import '../styles/pages.css';

const Login = () => {

  const navigate = useNavigate();
  const { login } = useAuth();

  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const [selectedRole, setSelectedRole] = useState("");
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  // ================= VALIDATION =================
  const validateForm = () => {
    const newErrors = {};

    if (!formData.email) {
      newErrors.email = 'Email is required';
    } else if (!validateEmail(formData.email)) {
      newErrors.email = 'Please enter a valid email';
    }

    if (!formData.password) {
      newErrors.password = 'Password is required';
    } else if (formData.password.length < 6) {
      newErrors.password = 'Password must be at least 6 characters';
    }

    return newErrors;
  };

  // ================= HANDLE CHANGE =================
  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));

    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: ''
      }));
    }
  };

  // ================= HANDLE LOGIN =================
  const handleSubmit = async (e) => {
    e.preventDefault();

    const validationErrors = validateForm();

    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    // ⭐ OPTIONAL: force role selection (you can remove if backend handles it)
    if (!selectedRole) {
      alert("Please select a role (Donor or Receiver)");
      return;
    }

    setLoading(true);

    try {

      const response = await loginUser({
        email: formData.email,
        password: formData.password
      });

      const token =
        response?.token ||
        response?.data?.token ||
        response?.jwt ||
        response?.accessToken;

      const user =
        response?.user ||
        response?.data?.user ||
        response?.data;

      const backendRole =
        (response?.role ||
          response?.data?.role ||
          user?.role ||
          "").toUpperCase();

      if (!token || !user) {
        alert('Invalid server response');
        return;
      }

      login(user, token);

      localStorage.setItem("token", token);
      localStorage.setItem("user", JSON.stringify(user));
      console.log("Login Successful:", { user, token, backendRole });

      // ⭐ FINAL ROLE DECISION (Backend priority)
      const role = (backendRole || selectedRole).toUpperCase();

      localStorage.setItem("role", role);

      alert(`Login Successful as ${role} ✅`);

      // ================= ROLE BASED REDIRECT =================
      switch (role) {

        case "DONOR":
          navigate('/donor/dashboard', { replace: true });
          break;

        case "RECEIVER":
          navigate('/receiver/available-foods', { replace: true });
          break;

        case "ADMIN":
          navigate('/admin/dashboard', { replace: true });
          break;

        default:
          navigate('/dashboard', { replace: true });
      }

    } catch (error) {
      alert(
        error?.response?.data?.message ||
        error?.message ||
        'Login Failed ❌'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-container">

      {/* LEFT SIDE */}
      <div className="login-left">
        <div className="left-content">
          <h1>Share Food, Spread Humanity ❤️</h1>
          <p>Join our Food Donation System and help reduce hunger.</p>
        </div>
      </div>

      {/* RIGHT SIDE */}
      <div className="login-right">

        <div className="auth-card">

          <h2>Welcome Back 👋</h2>
          <p>Login to continue</p>

          <form onSubmit={handleSubmit}>

            {/* EMAIL */}
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className={errors.email ? 'input-error' : ''}
              />
              {errors.email && <span className="error-text">{errors.email}</span>}
            </div>

            {/* PASSWORD */}
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className={errors.password ? 'input-error' : ''}
              />
              {errors.password && <span className="error-text">{errors.password}</span>}
            </div>

            {/* ROLE SELECTION */}
            <div className="form-group">
              <label>Choose Role</label>

              <div className="role-buttons">

                <button
                  type="button"
                  className={selectedRole === "DONOR" ? "role-btn active donor" : "role-btn"}
                  onClick={() => setSelectedRole("DONOR")}
                >
                  DONOR
                </button>

                <button
                  type="button"
                  className={selectedRole === "RECEIVER" ? "role-btn active receiver" : "role-btn"}
                  onClick={() => setSelectedRole("RECEIVER")}
                >
                  RECEIVER
                </button>

              </div>
            </div>

            <button className="auth-btn" disabled={loading}>
              {loading ? 'Logging in...' : 'Login'}
            </button>

          </form>

          <p className="auth-link">
            Don't have an account? <Link to="/register">Register</Link>
          </p>

        </div>

      </div>
    </div>
  );
};

export default Login;