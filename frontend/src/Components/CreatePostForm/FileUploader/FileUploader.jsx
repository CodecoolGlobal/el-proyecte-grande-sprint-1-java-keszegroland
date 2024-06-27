import React, { useState } from 'react';
import Dropzone from 'react-dropzone';
import "./FileUploader.css";

function fetchImages(formData) {
	return fetch("https://api.escuelajs.co/api/v1/files/upload", {
		method: "POST",
		body: formData
	}).then((res) => res.json());
}

function FileUploader() {
	const [file, setFile] = useState(null);
	const [preview, setPreview] = useState(null);

	const handleUpload = (acceptedFiles) => {
		console.log("Logging dropped/selected files:", acceptedFiles);
		const formData = new FormData();
		formData.append("file", acceptedFiles[0]);

		fetchImages(formData).then((res) => {
			if (res) {
				setFile(acceptedFiles[0]);
				setPreview(URL.createObjectURL(acceptedFiles[0]));
			} else {
				console.error(res);
			}
		})
			.catch((error) => {
				console.error(error);
			});
	};

	return (
		<div className="file-uploader">
			<div className='file_uploader_container'>
				<Dropzone onDrop={handleUpload} accept={"image/*"}>
					{({ getRootProps, getInputProps, open, isDragAccept, isDragReject }) => {
						const additionalClass = isDragAccept ? "accept" : isDragReject ? "reject" : "";
						return (
							<div {...getRootProps({ className: `dropzone ${additionalClass}` })}>
								<input {...getInputProps()} style={{ display: 'none' }} />
								<img className='file-picture' alt='file-upload' src={'/Assets/file-upload.svg'} />
								<h1 className='drag-photo-text'>Drag photo here</h1>
								<p className='file-format-text'>SVG, PNG, JPEG</p>
								<button type="button" className='select-from-computer-button' onClick={open}>
									Select from computer
								</button>
							</div>
						);
					}}
				</Dropzone>
			</div>
			{preview && (
				<div className='uploaded-picture-container'>
					<h4>File uploaded successfully</h4>
					<img src={preview} className='uploaded-picture' alt='uploaded img' />
				</div>
			)}
		</div>
	);
}

export default FileUploader;
