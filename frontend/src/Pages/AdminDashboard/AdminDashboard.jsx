import { useEffect, useState } from "react";
import AdminNavBar from "../../Components/AdminNavBar/AdminNavBar";
import UsersTable from "../../Components/AdminPageTables/UserTable/UsersTable";
import PostTable from "../../Components/AdminPageTables/PostTable/PostTable";
import { useGetToken } from "../../Components/CustomHook/CustomHook";
import "./adminDashboard.css";

async function fetchRequest(url, token) {
  const response = await fetch(url, {
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
      "Authorization": `Bearer ${token}`
    }
  });
  return await response.json();
}

function AdminDashboard() {
  const [members, setMembers] = useState([]);
  const [posts, setPosts] = useState([]);
  const [pageState, setPageState] = useState("");
  const token = useGetToken();

  useEffect(() => {
    async function handleFetchMembers() {
      const response = await fetchRequest("/api/admin/getAllMember", token);
      setMembers(response);
    }
    handleFetchMembers();
  }, [token]);

  useEffect(() => {
    if (pageState === "reportedPosts") {
      async function handleFetchReportedPosts() {
        const response = await fetchRequest("/api/admin/posts", token);
        setPosts(response);
      }
      handleFetchReportedPosts();
    }
  }, [pageState, token]);

  const handleAdminPromotion = async (username) => {
    const promotedMember = await handleRequest(`/api/admin/promote/${username}`, "PUT", token);
    setMembers(prevMembers => prevMembers.map(member => member.username === username ? promotedMember : member));
  };

  const handlePageState = (state) => {
    setPageState(state);
  };

  const handleDeleteMember = async (memberId) => {
    await handleRequest(`/api/admin/deleteMember/${memberId}`, "DELETE", token);
    setMembers(previousMembers => previousMembers.filter(member => member.publicId !== memberId));
  };

  const handleDeletePost = async (postId) => {
    await handleRequest(`/api/admin/deletePost/${postId}`, "DELETE", token);
    setPosts(previousPosts => previousPosts.filter(post => post.publicId !== postId));
  };

  return (
    <div className="admin-dashboard">
      <AdminNavBar onHandlePageState={handlePageState} />
      {pageState === "reportedPosts" ? (
        <PostTable reportedPosts={posts} onDelete={handleDeletePost} />
      ) : (
        <UsersTable members={members} onPromote={handleAdminPromotion} onDelete={handleDeleteMember} />
      )}
    </div>
  );
}

export default AdminDashboard;
