import "./adminNavBar.css";
import { FaTable } from "react-icons/fa";
import { BsPostageFill } from "react-icons/bs";
import { TbLogout } from "react-icons/tb";
import { useNavigate } from "react-router-dom";

function AdminNavBar() {
  const navigate = useNavigate("");

  function handleLogout() {
    localStorage.removeItem('jwtToken');
    navigate('/login');
  }

  return <div className="admin-nav-bar">
    <h1 className="navbar-header">Dashboard</h1>
    <ul>
      <li>
        <FaTable className="icon" />
        <span>Users</span>
      </li>
      <li>
        <BsPostageFill className="icon" />
        <span>Posts</span>
      </li>
      <li onClick={() => handleLogout()}><TbLogout className="icon" /><span>Logout</span></li>
    </ul>
  </div >
}

export default AdminNavBar;