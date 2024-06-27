import { useState } from "react";
import "./CreatePostForm.css";
import "../FormStyling.css";
import Loading from "../Loading/Loading";
import { useNavigate } from "react-router";
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
	const userId = "e32af39c-c1cb-429d-bd1b-67f4bf09afa5";
	const [description, setDescription] = useState("");
	const [picture, setPicture] = useState(null);

	const handleCreatePost = (e) => {
		e.preventDefault();
		setLoading(true);
		const post = {
			description: description,
			picture: picture
		};
		createPost(post, userId)
			.then(() => {
				setLoading(false);
				navigate("/");
			})
	}

	const handleUpload = (base64String) => {
		setPicture(base64String);
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
				<FileUploader onUpload={handleUpload} />

				<div className="buttons">
					<button className="formBtn" type="submit">Create Post</button>
					<button className="formBtn" type="button" onClick={() => navigate("/")}>Cancel</button>
				</div>
			</form>
		</div>
	);
};

export default CreatePostForm;