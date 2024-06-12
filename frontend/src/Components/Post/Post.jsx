import { useEffect, useState } from "react"
import "./Post.css";
import { FaRegPaperPlane } from "react-icons/fa";
import { PiHeartThin } from "react-icons/pi";
import { FcLike } from "react-icons/fc";

const fetchPosts = () => {
    return fetch('/api/post').then(res => res.json());
}


function Post() {
    const [posts, setPosts] = useState([])
    const [liked, setLiked] = useState(false)

    useEffect(() => {
        fetchPosts()
            .then(posts => {
                setPosts(posts)
            })
    }, []);

    const handlePostComment = () => {

    }

    const handleLike = () => {
        setLiked(!liked);
    }

    return (
        <div className="post">
            {posts.map(post => (
                <div key={post.username} className="onePost">
                    <div className="postContent">
                        <p className="username"><b>{post.username}</b></p>
                        <img
                            className="images"
                            src={post.picture}
                            alt="Kiselefánt" />
                        <div>{liked ? <FcLike className="heart" onClick={handleLike} /> : <PiHeartThin className="heart" onClick={handleLike} />}</div>

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