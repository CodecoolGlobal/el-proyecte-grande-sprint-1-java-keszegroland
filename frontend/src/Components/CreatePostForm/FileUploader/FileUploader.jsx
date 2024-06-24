import React, { useCallback, useState } from 'react';
import Dropzone, { useDropzone } from 'react-dropzone';
import "./FileUploader.css";

function FileUploader() {
	const [fileUrl, setFileUrl] = useState("");
	const [file, setFile] = useState([]);

	const onDrop = useCallback(acceptedFiles => {
		setFile(acceptedFiles);

	}, [])
	const { getRootProps, getInputProps } = useDropzone({
		onDrop,
		accept: {
			'image/*': ['.png', '.jpg', '.jpeg', '.svg']
		}

	})

	return (
		<div className="file-uploader">
			<div {...getRootProps()}>
				<input {...getInputProps()} />
				{
					fileUrl ? (
						<div>
							test1
						</div>
					) : (
						<div className='file_uploader_container'>
							<img className='file-picture' alt='file-upload' src={'/Assets/file-upload.svg'}></img>
							<h1 className='drag-photo-text'>Drag photo here</h1>
							<p className='file-format-text'>SVG, PNG, JPEG</p>
							<button className='select-from-computer-button'>Select from computer</button>
						</div>
					)
				}
			</div>
		</div >
	);
}

export default FileUploader;