import { useState } from "react";
import { PiHeartThin } from "react-icons/pi";
import { FcLike } from "react-icons/fc";
import { formatDistanceToNowStrict } from 'date-fns';
import { FaRegPaperPlane } from "react-icons/fa";
import HeartAnimation from "./HeartAnimation";
import "./Post.css";
import Options from "./Options";

function OnePost({ post }) {
    const [isAnimating, setIsAnimating] = useState(false);
    const [likes, setLikes] = useState(0);
    const [isLiked, setIsLiked] = useState(false);
    const [clicked, setClicked] = useState(false);

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

    const handleClick = () => {
        setClicked(!clicked)
    }

    const handlePostComment = () => {

    }
    return (
        <div key={post.publicId} className="onePost" >
            <p className="username"><b>{post.username}</b> ~ <i><span>{formatDistanceToNowStrict(post.creationDate)}</span> ago</i></p>
            <button className="threeDot" onClick={handleClick}>...</button>
            {clicked ? <Options postId={post.publicId} /> : <span></span>}
            <div className="photoDiv" onDoubleClick={handleDoubleClick}>
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