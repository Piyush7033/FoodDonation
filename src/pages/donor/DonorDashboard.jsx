import React from 'react';
import { Link } from 'react-router-dom';
import '../../styles/donor.css';

const DonorDashboard = () => {
  return (
    <div className="donor-container">

      {/* ================= HEADER ================= */}
      <div className="donor-header">

        <h1 className="donor-title">🍱 Donor Dashboard</h1>

        <p className="donor-subtitle">
          Manage your food donations and help reduce hunger ❤️
        </p>

      </div>

      {/* ================= STATS SECTION ================= */}
      <div className="donor-stats">

        <div className="stat-card">
          <h2>🍽</h2>
          <h3>0</h3>
          <p>Total Donations</p>
        </div>

        <div className="stat-card">
          <h2>📦</h2>
          <h3>0</h3>
          <p>Active Donations</p>
        </div>

        <div className="stat-card">
          <h2>❤️</h2>
          <h3>0</h3>
          <p>Lives Helped</p>
        </div>

      </div>

      {/* ================= MAIN GRID ================= */}
      <div className="donor-grid">

        <Link to="/donor/add-food" className="donor-card">

          <div className="card-icon">➕</div>

          <h3>Add Food</h3>

          <p>Post extra food for donation</p>

        </Link>

        <Link to="/donor/my-donations" className="donor-card">

          <div className="card-icon">🍛</div>

          <h3>My Donations</h3>

          <p>Track and manage your active donations</p>

        </Link>

        <Link to="/donor/history" className="donor-card">

          <div className="card-icon">📜</div>

          <h3>Donation History</h3>

          <p>View all past donation records</p>

        </Link>

      </div>

    </div>
  );
};

export default DonorDashboard;