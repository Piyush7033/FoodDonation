import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import '../styles/pages.css';

const HomePage = () => {

  const { isLoggedIn } = useAuth();
  const navigate = useNavigate();

  return (

    <div className="home-container">

      {/* ================= TOP HERO (CENTER FOCUS) ================= */}
      <div className="hero">

        <h1>
          🍲 Food Donation System
        </h1>

        <p>
          Connect donors and receivers to reduce food waste and fight hunger ❤️
        </p>

        <div className="hero-buttons">

          {!isLoggedIn ? (
            <>
              <Link to="/register" className="btn primary-btn">
                Get Started
              </Link>

              {/* <Link to="/login" className="btn secondary-btn">
                Login
              </Link> */}
            </>
          ) : (
            <Link to="/dashboard" className="btn primary-btn">
              Go to Dashboard
            </Link>
          )}

        </div>

      </div>

      {/* ================= MIDDLE ROLE CARDS ================= */}
      {/* <div className="role-section">

        <h2>Choose Role</h2>

        <div className="role-buttons">

          <button
            className="role-btn donor"
            onClick={() => navigate('/donor/dashboard')}
          >
            🍱 Donor
          </button>

          <button
            className="role-btn receiver"
            onClick={() => navigate('/receiver/dashboard')}
          >
            ❤️ Receiver
          </button>

        </div>

      </div> */}

      {/* ================= BOTTOM FEATURES ================= */}
      <div className="features-section">

        <h2>How It Works</h2>

        <div className="features-grid">

          <div className="feature-card">
            👨‍🍳 
            Donors<br />
            <small>A donor helps needy people by giving food, money, or clothes. Their generosity supports communities, <br /> spreads hope, and improves society. Every small or big donation creates a positive impact.</small>
          </div>

          <div className="feature-card">
            🙋‍♀️
            Receivers<br />
            <small>Receivers in food donation systems collect and distribute donated food to needy people.  NGOs and charities ensure safe delivery, <br /> reduce food wastage, support poor communities, and connect donors with those in need effectively.</small>
          </div>

          <div className="feature-card">
            📊 
            System<br />
            <small>Track everything</small>
          </div>

        </div>

      </div>

    </div>

  );
};

export default HomePage;