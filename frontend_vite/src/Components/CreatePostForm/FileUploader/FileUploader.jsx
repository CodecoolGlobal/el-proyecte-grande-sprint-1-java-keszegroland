import React, { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import "./FileUploader.css";


function FileUploader({ onUpload }) {
	const [files, setFiles] = useState([]);

	const onDrop = useCallback(acceptedFiles => {
		if (acceptedFiles?.length) {
			const reader = new FileReader();
			reader.onload = () => {
				const base64String = reader.result.split(",")[1];
				setFiles(previousFiles => [
					...previousFiles,
					{ file: acceptedFiles[0], preview: reader.result, base64: base64String },
				]);
				onUpload(base64String);
			};

			reader.readAsDataURL(acceptedFiles[0]);
		}
	}, [onUpload]);

	const { getRootProps, getInputProps } = useDropzone({
		onDrop,
		accept: {
			'image/*': ['.png', '.jpg', '.jpeg', '.svg']
		}
	});

	return (
		<div className="file-uploader">
			{files.length === 0 ? (
				<div {...getRootProps()} className='file_uploader_container'>
					<input {...getInputProps()}></input>
					<img className='file-picture' alt='file-upload' src={'/Assets/file-upload.svg'}></img>
					<h1 className='drag-photo-text'>Drag photo here</h1>
					<p className='file-format-text'>SVG, PNG, JPEG</p>
					<button className='select-from-computer-button' onClick={e => e.preventDefault()}>Select From Computer</button>
				</div>
			) : (
				<ul className='uploaded-images'>
					{files.map(file => (
						<li key={file.file.name}>
							<img className='uploaded-image' src={file.preview} alt={file.file.name}></img>
						</li>
					))}
				</ul>
			)}
		</div>
	);

}

export default FileUploader;
