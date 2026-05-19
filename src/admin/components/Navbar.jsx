import { useNavigate } from 'react-router-dom';

import './Navbar.css';

const Navbar = () => {

  const navigate = useNavigate();

  // =========================
  // LOGOUT
  // =========================

  const handleLogout = () => {

    // REMOVE STORED DATA
    localStorage.removeItem('token');

    localStorage.removeItem('role');

    localStorage.removeItem('user');

    // REDIRECT TO LOGIN
    navigate('/login');
  };

  return (

    <div className="admin-navbar">

      <h1 className="admin-navbar-title">
        Food Donation Admin
      </h1>

      <button
        className="logout-btn"
        onClick={handleLogout}
      >
        Logout
      </button>

    </div>
  );
};

export default Navbar;