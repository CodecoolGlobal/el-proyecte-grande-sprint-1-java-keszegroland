import { useState } from "react";
import "./CreatePostForm.css";
import Loading from "../Loading/Loading";
import { useNavigate } from "react-router";
import { AiFillPicture } from "react-icons/ai";

function createPost(post, userId) {
	return fetch(`/api/posts/${userId}`, {
		method: "POST",
		headers: { "Content-Type": "application/json", },
		body: JSON.stringify(post),
	}).then((res) => res.json());
};

function CreatePostForm() {
	const navigate = useNavigate();
	const [loading, setLoading] = useState(false);
	const userId = 7;
	const [description, setDescription] = useState("");
	const [picture, setPicture] = useState("");

	const handleCreatePost = (e) => {
		e.preventDefault();
		setLoading(true);
		const post = { description, picture };
		createPost(post, userId)
			.then(() => {
				setLoading(false);
				navigate("/");
			})
	}

	if (loading) {
		return <Loading />
	}

	return (
		<div className="containerGlass">
			<form className="createPostForm" onSubmit={handleCreatePost}>
				<h1 id="header">Create Post</h1>
				<div className="textAreaInput">
					<textarea
						type="text"
						value={description}
						placeholder="Description"
						required
						onChange={(e) => setDescription(e.target.value)}
						id="description"
					/>
				</div>
				<div className="inputBox">
					<input
						type="text"
						value={picture}
						placeholder="Picture"
						onChange={(e) => setPicture(e.target.value)}
						id="picture"
					/>
					<AiFillPicture className="icon" />
				</div>

				<div className="buttons">
					<button type="submit">Create Post</button>
					<button type="button" onClick={() => navigate("/")}>Cancel</button>
				</div>
			</form>
		</div>
	);
};

export default CreatePostForm;