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
                <h1 id="header">Login</h1>
                <div className="input-box">
                    <input
                        type="text"
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
                        placeholder="Password"
                        required
                        onChange={(e) => setPassword(e.target.value)}
                        id="password"
                    />
                    <FaLock className="icon" />
                </div>

                <div className="buttons">
                    <button type="submit">Login</button>

                    <button type="button" onClick={() => navigate("/")}>Cancel</button>
                </div>
                <p className="linkToSignUp">Don't have an account? <Link className="linkToSignOrLogin" to={"/signup"}>Register</Link></p>
            </form >
        </div>
    );
}

export default Login;