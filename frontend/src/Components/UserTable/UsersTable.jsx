import "./usersTable.css";

function UsersTable({ members, onPromote }) {

  return <div className="user-table-container">
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Username</th>
          <th>Roles</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {members?.map((member) => (
          <tr key={member?.publicId}>
            <td>{member?.publicId}</td>
            <td>{member?.firstName} {member?.lastName} {member?.email}</td>
            <td>{member?.username}</td>
            <td>{member?.roles?.map((role) => `${role} `)}</td>
            <td><button>Delete</button><button>Update</button><button type="button" onClick={() => onPromote(member.username)}>Promote to Admin</button></td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
}

export default UsersTable;