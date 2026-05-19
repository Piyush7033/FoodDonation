import { Link } from 'react-router-dom';

import './Sidebar.css';

const Sidebar = () => {

  return (

    <div className="admin-sidebar">

      <h1 className="sidebar-title">
        Admin Panel
      </h1>

      <ul className="sidebar-menu">

        <li>
          <Link to="/admin/dashboard">
            Dashboard
          </Link>
        </li>

        <li>
          <Link to="/admin/donations">
            Donations
          </Link>
        </li>

        <li>
          <Link to="/admin/receivers">
            Receivers
          </Link>
        </li>

        <li>
          <Link to="/admin/users">
            Users
          </Link>
        </li>

      </ul>

    </div>
  );
};

export default Sidebar;