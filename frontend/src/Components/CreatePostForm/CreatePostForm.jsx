import { useState } from "react";
import "./CreatePostForm.css";
import "../FormStyling.css";
import Loading from "../Loading/Loading";
import { useNavigate } from "react-router";
import { AiFillPicture } from "react-icons/ai";
import FileUploader from "./FileUploader/FileUploader";

function createPost(post, userPublicId) {
	return fetch(`/api/posts/${userPublicId}`, {
		method: "POST",
		headers: { "Content-Type": "application/json", },
		body: JSON.stringify(post),
	}).then((res) => res.json());
};

function CreatePostForm() {
	const navigate = useNavigate();
	const [loading, setLoading] = useState(false);
	const userId = "c76dc986-4094-4fd7-bc95-487b72dd9727";
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
				<div className="input-box">
					<input
						type="text"
						value={picture}
						placeholder="Picture"
						onChange={(e) => setPicture(e.target.value)}
						id="picture"
					/>
					<div className="label-container">
						<label>Picture</label>
					</div>
					<AiFillPicture className="icon" />
				</div>
				<FileUploader />

				<div className="buttons">
					<button type="submit">Create Post</button>
					<button type="button" onClick={() => navigate("/")}>Cancel</button>
				</div>
			</form>
		</div>
	);
};

export default CreatePostForm;