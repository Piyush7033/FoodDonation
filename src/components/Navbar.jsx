import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import '../styles/navbar.css';

const Navbar = () => {

  const { user, isLoggedIn, logout } = useAuth();

  const navigate = useNavigate();

  const [menuOpen, setMenuOpen] = useState(false);

  // ==============================
  // LOGOUT
  // ==============================
  const handleLogout = () => {

    logout();

    navigate('/');
  };

  return (

    <nav className="navbar">

      <div className="navbar-container">

        {/* ================= LOGO ================= */}

        <Link
          to="/"
          className="navbar-logo"
        >
          🍲 Food Donation
        </Link>

        {/* ================= MENU ================= */}

        <div className={`nav-menu ${menuOpen ? 'active' : ''}`}>

          {!isLoggedIn ? (

            <div className="nav-links">

              <Link
                to="/login"
                className="nav-link"
              >
                Login
              </Link>

              <Link
                to="/register"
                className="nav-link btn-primary"
              >
                Register
              </Link>

            </div>

          ) : (

            <div className="nav-links">

              {/* ================= USER INFO ================= */}

              <span className="user-info">
                👋 Welcome, {user?.name}
              </span>

              {/* ================= COMMON ================= */}

              {/* <Link
                to="/dashboard"
                className="nav-link"
              >
                Dashboard
              </Link> */}

              {/* ================= DONOR ================= */}

              {user?.role === 'DONOR' && (

                <>

                  <Link
                    to="/donor/dashboard"
                    className="nav-link"
                  >
                    
                  </Link>

                  <Link
                    to="/donor/add-food"
                    className="nav-link"
                  >
                    Add Food
                  </Link>

                  <Link
                    to="/donor/my-donations"
                    className="nav-link"
                  >
                    My Donations
                  </Link>

                  <Link
                    to="/donor/history"
                    className="nav-link"
                  >
                    Donation History
                  </Link>

                </>
              )}

              {/* ================= RECEIVER ================= */}

              {user?.role === 'RECEIVER' && (

                <>

                  <Link
                    to="/receiver/dashboard"
                    className="nav-link"
                  >
                    Receiver Dashboard
                  </Link>

                  <Link
                    to="/receiver/available-foods"
                    className="nav-link"
                  >
                    Available Foods
                  </Link>

                  <Link
                    to="/receiver/requests"
                    className="nav-link"
                  >
                    My Requests
                  </Link>

                </>
              )}

              {/* ================= ADMIN ================= */}

              {user?.role === 'ADMIN' && (

                <>

                  <Link
                    to="/admin/dashboard"
                    className="nav-link"
                  >
                    Admin Dashboard
                  </Link>

                </>
              )}

              {/* ================= LOGOUT ================= */}

              <button
                onClick={handleLogout}
                className="logout-btn"
              >
                Logout
              </button>

            </div>
          )}

        </div>

      </div>

    </nav>
  );
};

export default Navbar;