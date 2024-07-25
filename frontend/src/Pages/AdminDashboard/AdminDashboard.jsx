import { useEffect, useState } from "react";
import AdminNavBar from "../../Components/AdminNavBar/AdminNavBar";
import UsersTable from "../../Components/UserTable/UsersTable";
import { useGetToken } from "../../Components/CustomHook/CustomHook";
import "./adminDashboard.css";

async function fetchMembers(token) {
  const response = await fetch("/admin/getAll", {
    headers: {
      "Content-Type": "application/json",
      'Authorization': `Bearer ${token}`
    }
  });
  return await response.json();
}

async function promoteToAdmin(username, token) {
  const response = await fetch(`/api/admin/promote/${username}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    }
  })
  return await response.json();
}


function AdminDashboard() {
  const [members, setMembers] = useState(null);
  const token = useGetToken();



  useEffect(() => {
    async function handleFetchMembers() {
      const response = await fetchMembers(token);
      setMembers(response);
    }
    handleFetchMembers();
  }, [token])

  const handleAdminPromotion = async (username) => {
    const promotedMember = await promoteToAdmin(username, token)
    setMembers(prevMembers => prevMembers.map(member => member.username === username ? promotedMember : member))
  }

  return <div className="admin-dashboard">
    <AdminNavBar />
    <UsersTable members={members} onPromote={handleAdminPromotion} />
  </div>
}

export default AdminDashboard;