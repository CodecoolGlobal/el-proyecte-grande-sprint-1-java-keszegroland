import { useState } from "react";
import "./SignUpForm.css";
import "../FormStyling.css";
import { Link, useNavigate } from "react-router-dom";
import Loading from "../Loading/Loading";
import { FaLock, FaUser } from "react-icons/fa";
import { MdAlternateEmail } from "react-icons/md";


function createMember(member) {
  return fetch("/api/member/signUp", {
    method: "POST",
    headers: { "Content-Type": "application/json", },
    body: JSON.stringify(member),
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


  const handleCreateMember = (e) => {
    e.preventDefault();
    setLoading(true);
    const member = { firstName, lastName, username, password, email };
    createMember(member)
      .then(() => {
        localStorage.removeItem("jwtToken");
        setLoading(false);
        navigate("/");
      })
  }

  if (loading) {
    return <Loading />
  }

  return (
    <div className="containerGlass">
      <form className="signUpForm" onSubmit={handleCreateMember}>
        <h1 id="header">Sign up</h1>
        <p id="sub-header">Create your account in seconds</p>
        <div className="input-box" id="full-name">
          <div className="name-part">
            <input
              type="text"
              value={firstName}
              placeholder="First Name"
              required
              onChange={(e) => setFirstName(e.target.value)}
              id="firstName"
            />
            <div className="label-container">
              <label>First Name</label>
            </div>
          </div>
          <div className="name-part">
            <input
              type="text"
              value={lastName}
              placeholder="Last Name"
              required
              onChange={(e) => setLastName(e.target.value)}
              id="lastName"
            />
            <div className="label-container">
              <label>Last Name</label>
            </div>
          </div>
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
          <div className="label-container">
            <label>Username</label>
          </div>
          <FaUser className="icon" />
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
          <div className="label-container">
            <label>Email Address</label>
          </div>
          <MdAlternateEmail className="icon" />
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
          <div className="label-container">
            <label>Password</label>
          </div>
          <FaLock className="icon" />
        </div>
        <button className="formBtn" type="submit">Create an account</button>

        <p className="linkToLogin">Already have an account?<Link className="linkToSignOrLogin" to={"/login"}>Log in</Link></p>
      </form>
    </div>
  );
}

export default SignUpForm;