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

async function handleRequest(url, methodType, token) {
  const response = await fetch(url, {
    method: methodType,
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    }
  })
  return await response.json();
}


function AdminDashboard() {
  const [members, setMembers] = useState([]);
  const token = useGetToken();

  useEffect(() => {
    async function handleFetchMembers() {
      const response = await fetchMembers(token);
      setMembers(response);
    }
    handleFetchMembers();
  }, [token])

  const handleAdminPromotion = async (username) => {
    const promotedMember = await handleRequest(`/api/admin/promote/${username}`, "PUT", token);
    setMembers(prevMembers => prevMembers.map(member => member.username === username ? promotedMember : member));
  }

  async function handleDeleteMember(memberId) {
    await handleRequest(`/api/member/delete/${memberId}`, "DELETE", token);
    setMembers(previousMembers => previousMembers.filter(member => member.publicId !== memberId))
  }

  return <div className="admin-dashboard">
    <AdminNavBar />
    <UsersTable members={members} onPromote={handleAdminPromotion} onDelete={handleDeleteMember} />
  </div>
}

export default AdminDashboard;