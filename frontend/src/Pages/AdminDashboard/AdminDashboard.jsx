import { useEffect, useState } from "react";
import AdminNavBar from "../../Components/AdminNavBar/AdminNavBar";
import UsersTable from "../../Components/UserTable/UsersTable";

async function fetchMembers() {
  const token = localStorage.getItem("token");
  const response = await fetch("/api/member/getAll", {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  return await response.json();
}

function AdminDashboard() {
  const [members, setMembers] = useState(null);

  useEffect(() => {
    async function handleFetchMembers() {
      const response = await fetchMembers();
      setMembers(response);
    }
    handleFetchMembers();
  }, [])

  return <div className="admin-dashboard">
    <AdminNavBar />
    <UsersTable members={members} />
  </div>
}

export default AdminDashboard;