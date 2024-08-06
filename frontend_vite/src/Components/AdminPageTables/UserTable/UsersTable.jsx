import "./usersTable.css";
import ".././adminPageTable.css";

function UsersTable({ members, onPromote, onDelete }) {

  return <div className="user-table-container">
    <h1 className="table-header">Members</h1>
    <table>
      <thead>
        <tr>
          <th>Id</th>
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
            <td>{member?.roles?.map((current) => `${current.role} `)}</td>
            <td>
              <button type="button" className="delete-button" onClick={() => onDelete(member?.publicId)}>Delete</button>
              <button type="button" className="promote-button" onClick={() => onPromote(member?.username)}>Promote to Admin</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
}

export default UsersTable;