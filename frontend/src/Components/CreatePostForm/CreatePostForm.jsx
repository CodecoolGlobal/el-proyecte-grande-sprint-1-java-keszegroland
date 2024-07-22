import { useState } from "react";
import "./CreatePostForm.css";
import "../FormStyling.css";
import Loading from "../Loading/Loading";
import { useNavigate } from "react-router";
import FileUploader from "./FileUploader/FileUploader";

function createPost(post, memberPublicId) {
	return fetch(`/api/posts/${memberPublicId}`, {
		method: "POST",
		headers: { "Content-Type": "application/json", },
		body: JSON.stringify(post),
	}).then((res) => res.json());
};

function CreatePostForm() {
	const navigate = useNavigate();
	const [loading, setLoading] = useState(false);
	const memberId = "2079a96b-e3ab-42de-8e9d-5be19e09d298";
	const [description, setDescription] = useState("");
	const [picture, setPicture] = useState(null);

	const handleCreatePost = (e) => {
		e.preventDefault();
		setLoading(true);
		const post = {
			description: description,
			picture: picture
		};
		createPost(post, memberId)
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