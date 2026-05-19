import React, { useEffect, useState } from 'react';

import Sidebar from '../components/Sidebar';
import Navbar from '../components/Navbar';

import API from '../../api/axios';

import './AdminDashboard.css';

const AdminDashboard = () => {

  // ================= STATE =================
  const [stats, setStats] = useState({
    totalUsers: 0,
    activeUsers: 0,
    disabledUsers: 0,
    totalFood: 0
  });

  const [loading, setLoading] = useState(true);

  // ================= FETCH DASHBOARD STATS =================
  const fetchDashboardStats = async () => {

    try {

      const token =
        localStorage.getItem("authToken");

      console.log("Admin Token:", token);

      const response = await API.get(
        "/admin/stats",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(
        "Dashboard Stats:",
        response.data
      );

      setStats(response.data);

    } catch (error) {

      console.error(
        "Dashboard Fetch Error:",
        error.response?.data || error.message
      );

    } finally {

      setLoading(false);
    }
  };

  // ================= LOAD DATA =================
  useEffect(() => {

    fetchDashboardStats();

  }, []);

  return (

    <div className="admin-layout">

      {/* SIDEBAR */}
      <Sidebar />

      {/* MAIN CONTENT */}
      <div className="admin-main">

        {/* NAVBAR */}
        <Navbar />

        {/* PAGE CONTENT */}
        <div className="admin-content">

          <h1 className="admin-heading">
            Admin Dashboard
          </h1>

          {/* LOADING */}
          {loading ? (

            <p className="loading-text">
              Loading dashboard...
            </p>

          ) : (

            <div className="dashboard-cards">

              {/* TOTAL USERS */}
              <div className="dashboard-card">

                <h2>Total Users</h2>

                <p>{stats.totalUsers}</p>

              </div>

              {/* ACTIVE USERS */}
              <div className="dashboard-card">

                <h2>Active Users</h2>

                <p>{stats.activeUsers}</p>

              </div>

              {/* DISABLED USERS */}
              <div className="dashboard-card">

                <h2>Disabled Users</h2>

                <p>{stats.disabledUsers}</p>

              </div>

              {/* TOTAL FOOD */}
              <div className="dashboard-card">

                <h2>Total Donations</h2>

                <p>{stats.totalFood}</p>

              </div>

            </div>
          )}

        </div>

      </div>

    </div>
  );
};

export default AdminDashboard;