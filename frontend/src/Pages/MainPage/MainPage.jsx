import { Link } from "react-router-dom";
import Post from "../../Components/Post/Post";
import "./MainPage.css";
import { FaHome, FaUserTie } from "react-icons/fa";
import { IoCreate } from "react-icons/io5";
import logo from "../../logo.png";
import blob from "../../blob-1-opacity-100.gif";
import { useEffect, useState } from "react";

function MainPage() {
    const [innerWidth, setInnerWidth] = useState(window.innerWidth);

    useEffect(() => {
        const handleResize = () => setInnerWidth(window.innerWidth)

        window.addEventListener('resize', handleResize)
        return () => {
            window.removeEventListener('resize', handleResize)
        };
    }, [])

    return (
        <div className="MainPage">
            <div className="watermark">
                <img className="logo" src={logo} alt="logo"></img>
                <p className="strings">Strings</p>
                <img className="blob" src={blob} alt="gif"></img>
            </div>
            <Post />
            <div className="links">
                <div className="linkDiv"><Link to={'/'} className='homeEmblem'><FaHome className="homepage-icon" /><span className="link-text">{innerWidth > 576 ? "Home page" : ""}</span></Link></div>
                <div className="linkDiv"><Link to={'/signup'} className='signupEmblem'><FaUserTie /><span className="link-text">{innerWidth > 576 ? "Sign up" : ""}</span></Link></div>
                <div className="linkDiv"><Link to={'/posts/create'} className='createAnswerEmblem'><IoCreate /><span className="link-text">{innerWidth > 576 ? "Create new Post" : ""}</span></Link></div>
            </div>
        </div>
    );
}

export default MainPage;