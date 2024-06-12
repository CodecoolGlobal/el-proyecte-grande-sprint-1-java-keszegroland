import { useState } from "react";
import { PiHeartThin } from "react-icons/pi";
import { FcLike } from "react-icons/fc";
import "./Post.css";



function Like() {
    const [isLiked, setIsLiked] = useState(false);

    const handleLike = () => {
        setIsLiked(!isLiked);
    }

    return (
        <div>{isLiked ? <FcLike className="heart" onClick={handleLike} /> : <PiHeartThin className="heart" onClick={handleLike} />}</div>
    )
}


export default Like;