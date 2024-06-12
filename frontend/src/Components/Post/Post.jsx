import { useEffect, useState } from "react"
import "./Post.css";
import { FaRegPaperPlane } from "react-icons/fa";
import Like from "./Like";

const fetchPosts = () => {
    return fetch('/api/posts').then(res => res.json());
}


function Post() {
    const [posts, setPosts] = useState([])

    useEffect(() => {
        fetchPosts()
            .then(posts => {
                setPosts(posts)
            })
    }, []);

    const handlePostComment = () => {

    }

    return (
        <div className="post">
            {posts.map(post => (
                <div key={post.creation_date} className="onePost">
                    <div className="postContent">
                        <p className="username"><b>{post.username}</b></p>
                        <img
                            className="images"
                            src={post.picture}
                            alt="Kiselefánt" />
                        <Like />
                        <div>{post.creation_date.split('T').join(' ').slice(0, -10)}</div>
                        <p>{post.description}</p>
                        <form>
                            <input className="commentHere" placeholder="Comment here" name="comment" />
                            {<FaRegPaperPlane onClick={handlePostComment} />}
                        </form>
                        <p>View all comments</p>
                    </div>
                </div>

            ))}
        </div>
    )
}

export default Post