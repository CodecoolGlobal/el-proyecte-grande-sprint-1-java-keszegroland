import { useEffect, useState } from "react"
import "./Post.css";
import Loading from "../Loading/Loading";
import OnePost from "./OnePost";
import cloud from "../../cloudy.png";

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

	if (loading) {
		return <Loading />
	}

	return (
		<div className="posts">
			<h1 className="homeFeed">Home Feed</h1>
			{posts.map(post => (
				<section key={post.publicId}>
					<OnePost post={post} />
					<img src={cloud} alt="cloud" className="cloud"></img>
				</section>
			))}
		</div>
	)
}

export default Post