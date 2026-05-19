import React from 'react';
import { Link } from 'react-router-dom';
import '../../styles/receiver.css';

const ReceiverDashboard = () => {
  return (
    <div className="receiver-container">

      {/* ================= HEADER ================= */}
      <div className="receiver-header">

        <h1 className="receiver-title">❤️ Receiver Dashboard</h1>

        <p className="receiver-subtitle">
          Find available food and request meals instantly 🍱
        </p>

      </div>

      {/* ================= QUICK STATS ================= */}
      <div className="receiver-stats">

        <div className="stat-card">
          <h2>🍱</h2>
          <p>Available Food</p>
        </div>

        <div className="stat-card">
          <h2>📋</h2>
          <p>My Requests</p>
        </div>

        <div className="stat-card">
          <h2>❤️</h2>
          <p>Saved Meals</p>
        </div>

      </div>

      {/* ================= MAIN ACTION CARDS ================= */}
      <div className="receiver-grid">

        <Link to="/receiver/foods" className="receiver-card">

          <div className="card-icon">🍱</div>

          <h3>Available Foods</h3>

          <p>Browse fresh donated food from donors near you</p>

        </Link>

        <Link to="/receiver/requests" className="receiver-card">

          <div className="card-icon">📋</div>

          <h3>My Requests</h3>

          <p>Track status of your food requests in real time</p>

        </Link>

      </div>

    </div>
  );
};

export default ReceiverDashboard;