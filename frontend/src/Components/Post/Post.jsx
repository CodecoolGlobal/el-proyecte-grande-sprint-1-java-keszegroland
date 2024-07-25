import { useEffect, useState } from "react"
import "./Post.css";
import Loading from "../Loading/Loading";
import OnePost from "./OnePost";
import cloud from "../../cloudy.png";
import { useGetToken } from "../CustomHook/CustomHook";
import { ToastContainer } from 'react-toastify';

const fetchPosts = async (token) => {
	const res = await fetch('/api/post', {
		method: "GET",
		headers: { "Authorization": `Bearer ${token}` },
	});
	const data = await res.json();
	return data;
}


function Post() {
	const [posts, setPosts] = useState([])
	const [loading, setLoading] = useState(false);
	const token = useGetToken()

	useEffect(() => {
		setLoading(true);
		// fetchPosts(token)
		// 	.then(posts => {
		// 		setPosts(posts)
		// 		setLoading(false);
		// 	})
		const fetchMainPosts = async () => {
			const data = await fetchPosts(token);
			setPosts(data);
			setLoading(false);
		}
		fetchMainPosts();
	}, [token]);

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
			<ToastContainer
				position="bottom-right"
				autoClose={5000}
				hideProgressBar={false}
				newestOnTop={false}
				closeOnClick
				rtl={false}
				pauseOnFocusLoss
				draggable
				pauseOnHover
				theme="light"
			/>
		</div>
	)
}

export default Post