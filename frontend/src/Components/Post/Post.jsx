import { useEffect, useState } from "react"
import "./Post.css";
import Loading from "../Loading/Loading";
import OnePost from "./OnePost";


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
			{posts.map(post => (
				<OnePost post={post} />

			))}
		</div>
	)
}

export default Post