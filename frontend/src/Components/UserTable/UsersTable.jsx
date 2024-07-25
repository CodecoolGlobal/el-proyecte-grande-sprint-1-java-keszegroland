import "./usersTable.css";

function UsersTable({ members }) {

  return <div className="user-table-container">
    <h1 className="table-header">Members</h1>
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
            <td><p className="full-name">{member?.firstName} {member?.lastName}</p> <span className="email">{member?.email}</span></td>
            <td>{member?.username}</td>
            {member?.roles?.map((role) => <td key={role}>{role}</td>)}
            <td><button className="delete-button">Delete</button></td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
}

export default UsersTable;