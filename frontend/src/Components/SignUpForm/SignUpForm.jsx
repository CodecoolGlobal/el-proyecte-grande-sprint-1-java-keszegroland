import { useState } from "react";
import "./signUpForm.css";
import { Link, useNavigate } from "react-router-dom";
import Loading from "../Loading/Loading";
import { FaLock, FaUser } from "react-icons/fa";
import { MdAlternateEmail } from "react-icons/md";


function createUser(user) {
	return fetch("/api/users", {
		method: "POST",
		headers: { "Content-Type": "application/json", },
		body: JSON.stringify(user),
	}).then((res) => res.json());
};

function SignUpForm() {
	const navigate = useNavigate();
	const [loading, setLoading] = useState(false);
	const [firstName, setFirstName] = useState("");
	const [lastName, setLastName] = useState("");
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [email, setEmail] = useState("");


	const handleCreateUser = (e) => {
		e.preventDefault();
		setLoading(true);
		const user = { firstName, lastName, username, password, email };
		createUser(user)
			.then(() => {
				setLoading(false);
				navigate("/user/login");
			})
	}

	if (loading) {
		return <Loading />
	}

	return (
		<div className="containerGlass">
			<form className="signUpForm" onSubmit={handleCreateUser}>
				<h1 id="header">Sign Up</h1>
				<div className="input-box">
					<input
						type="text"
						value={firstName}
						placeholder="First Name"
						required
						onChange={(e) => setFirstName(e.target.value)}
						id="firstName"
					/>
				</div>
				<div className="input-box">
					<input
						type="text"
						value={lastName}
						placeholder="Last Name"
						required
						onChange={(e) => setLastName(e.target.value)}
						id="lastName"
					/>
				</div>
				<div className="input-box">
					<input
						type="text"
						value={username}
						placeholder="Username"
						required
						onChange={(e) => setUsername(e.target.value)}
						id="username"
					/>
					<FaUser className="icon" />
				</div>
				<div className="input-box">
					<input
						type="password"
						value={password}
						placeholder="Password"
						required
						onChange={(e) => setPassword(e.target.value)}
						id="password"
					/>
					<FaLock className="icon" />
				</div>
				<div className="input-box">
					<input
						type="email"
						value={email}
						placeholder="Email"
						required
						onChange={(e) => setEmail(e.target.value)}
						id="email"
					/>
					<MdAlternateEmail className="icon" />
				</div>

				<div className="buttons">
					<button type="submit">Sign up</button>
					<button type="button" onClick={() => navigate("/")}>Cancel</button>
				</div>
				<p className="linkToLogin">Already have an account?<Link className="linkToSignOrLogin" to={"/user/login"}>Login</Link></p>
			</form>
		</div>
	);
}

export default SignUpForm;