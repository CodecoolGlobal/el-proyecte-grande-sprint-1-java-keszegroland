import { useState } from "react";
import "./CreatePostForm.css";
import "../FormStyling.css";
import Loading from "../Loading/Loading";
import { useNavigate } from "react-router";
import FileUploader from "./FileUploader/FileUploader";
import { useGetToken } from "../CustomHook/CustomHook";

async function createPost(post, token) {
	const res = await fetch("/api/post", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"Authorization": "Bearer " + token
		},
		body: JSON.stringify(post),
	});
	const data = await res.json();
	return data;
};

function CreatePostForm() {
	const navigate = useNavigate();
	const [loading, setLoading] = useState(false);
	const [description, setDescription] = useState("");
	const [picture, setPicture] = useState(null);
	const token = useGetToken();

	const handleCreatePost = (e) => {
		e.preventDefault();
		setLoading(true);
		const post = {
			description: description,
			picture: picture
		};
		createPost(post, token)
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