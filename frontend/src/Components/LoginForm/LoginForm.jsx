import { useState } from "react";
import "./LoginForm.css";
import "../FormStyling.css";
import { Link, useNavigate } from "react-router-dom";
import Loading from "../Loading/Loading";
import { FaLock, FaUser } from "react-icons/fa";

const loginUser = (user) => {
    return fetch("/api/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json", },
        body: JSON.stringify(user)
    }).then(res => res.json());
}

function Login() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [user, setUser] = useState(null);

    const handleLoginUser = (user) => {
        setLoading(true);
        loginUser(user)
            .then((user) => {
                setLoading(false);
                setUser(user);
                navigate("/");
            })
    };

    const onSubmit = (e) => {
        e.preventDefault();
        return handleLoginUser({
            username,
            password,
        });
    };

    if (loading) {
        return <Loading />
    }

    return (
        <div className="containerGlass">
            <form className="LoginForm" onSubmit={onSubmit}>
                <h1 id="header">Log in</h1>
                <p id="sub-header">Please enter your details to access your account.</p>
                <div className="logo-container">
                    <img className="sc-logo" alt="google-logo" src="./Assets/google.svg"></img>
                    <img className="sc-logo" alt="apple-logo" src="./Assets/apple.svg"></img>
                    <img className="sc-logo" alt="twitter-logo" src="./Assets/twitter.svg"></img>
                </div>
                <div className="line-container">
                    <hr />
                    <p className="or">OR</p>
                    <hr />
                </div>
                <div className="input-box">
                    <input
                        type="text"
                        placeholder="Username"
                        required
                        onChange={(e) => setUsername(e.target.value)}
                        id="username"
                    />
                    <div className="label-container">
                        <label>Username</label>
                    </div>
                    <FaUser className="icon" />
                </div>

                <div className="input-box">
                    <input
                        type="password"
                        placeholder="Password"
                        required
                        onChange={(e) => setPassword(e.target.value)}
                        id="password"
                    />
                    <div className="label-container">
                        <label>Password</label>
                    </div>
                    <FaLock className="icon" />
                </div>
                <button type="submit">Log in</button>
                <p className="linkToSignUp">Don't have an account? <Link className="linkToSignOrLogin" to={"/signup"}>Sign up now</Link></p>
            </form >
        </div>
    );
}

export default Login;