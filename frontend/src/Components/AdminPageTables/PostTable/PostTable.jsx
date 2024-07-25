import "./postTable.css";
import ".././adminPageTable.css";

function PostTable({ reportedPosts, onDelete }) {
  return <div className="post-table-container">
    <h1 className="table-header">Reported Posts</h1>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Username</th>
          <th>Description</th>
          <th>picture</th>
          <th>creation Date</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        {reportedPosts?.map((post) => (
          <tr key={post?.publicId}>
            <td>{post?.publicId}</td>
            <td>{post?.username}</td>
            <td>{post?.description}</td>
            <td className="pictureCell"><img className="postPicture" src={post?.picture} alt="postPicture"></img></td>
            <td>{post?.creationDate?.split("T")[0]}</td>
            <td>
              <button type="button" className="delete-button" onClick={() => onDelete(post?.publicId)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
}

export default PostTable;