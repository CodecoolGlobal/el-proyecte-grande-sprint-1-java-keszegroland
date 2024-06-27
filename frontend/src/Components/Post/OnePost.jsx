import { useState } from "react";
import { PiHeartThin } from "react-icons/pi";
import { FcLike } from "react-icons/fc";
import { formatDistanceToNowStrict } from 'date-fns';
import { FaRegPaperPlane } from "react-icons/fa";
import HeartAnimation from "./HeartAnimation";
import "./Post.css";

function OnePost({ post }) {
    const [isAnimating, setIsAnimating] = useState(false);
    const [likes, setLikes] = useState(0);
    const [isLiked, setIsLiked] = useState(false);

    const handleLike = () => {
        setIsLiked(!isLiked);
    }

    const handleDoubleClick = () => {
        setLikes(likes + 1);
        setIsAnimating(true);
        setIsLiked(true);
        setTimeout(() => {
            setIsAnimating(false);
        }, 800);
    }

    const handlePostComment = () => {

    }
    return (
        <div key={post.creationDate} className="onePost" onDoubleClick={handleDoubleClick}>
            <p className="username"><b>{post.username}</b> ~ <i><span>{formatDistanceToNowStrict(post.creationDate)}</span> ago</i></p>
            <div className="photoDiv">
                <HeartAnimation isAnimating={isAnimating} />
                <img
                    className="images"
                    src={post.picture}
                    alt={post.description} />
            </div>
            <div>{isLiked ? <FcLike className="heart" onClick={handleLike} /> : <PiHeartThin className="heart" onClick={handleLike} />}</div>
            <p><b className="descUser">{post.username}: </b>{post.description}</p>
            <form className="comment">
                <input className="commentHere" placeholder="Comment here" name="comment" />
                {<FaRegPaperPlane onClick={handlePostComment} />}
            </form>
            <p className="allComment">View all comments</p>
        </div>
    )
}

export default OnePost;