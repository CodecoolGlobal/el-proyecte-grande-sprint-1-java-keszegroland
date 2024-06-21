import { useEffect, useState } from "react"
import "./Post.css";
import { FaRegPaperPlane } from "react-icons/fa";
import Like from "./Like";
import Loading from "../Loading/Loading";

const fetchPosts = () => {
	return fetch('/api/posts').then(res => res.json());
}


function Post() {
	const [posts, setPosts] = useState([])
	const [loading, setLoading] = useState(false);

	useEffect(() => {
		setLoading(true);
		fetchPosts()
			.then(posts => {
				setPosts(posts)
				setLoading(false);
			})
	}, []);

	const handlePostComment = () => {

	}

	if (loading) {
		<Loading />
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
						<div>{post.creationDate.split('T').join(' ').slice(0, -10)}</div>
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