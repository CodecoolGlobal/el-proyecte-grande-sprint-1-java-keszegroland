import { Link } from "react-router-dom";
import Post from "../../Components/Post/Post";
import "./MainPage.css";
import { FaHome, FaUserTie } from "react-icons/fa";
import { IoCreate } from "react-icons/io5";
//import logo from "../../logo.PNG";
import logo from "../../blackLogo.PNG";
// import blob from "../../blob-1-opacity-100.gif";
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
                {/* <img className="blob" src={blob} alt="gif"></img> */}
                <section>
                    <div className="blob">
                        <svg className="svgBlob" viewBox="0 0 80 80" xmlns="http://www.w3.org/2000/svg">
                            <defs>
                                <linearGradient id="gradient" gradientTransform="rotate(0)">
                                    <stop offset="0%" style={{ stopColor: "	rgb(0, 237, 255)" }}></stop>
                                    <stop offset="100%" style={{ stopColor: "rgb(255, 119, 244)" }}></stop>
                                </linearGradient>
                            </defs>
                            <path fill="url(#gradient)" className="blobColor">
                                <animate attributeName="d" dur="50000ms" repeatCount="indefinite" values="M44.2,-78C56,-69.7,63.6,-55.4,70.7,-41.4C77.8,-27.4,84.3,-13.7,86.1,1C87.8,15.7,84.8,31.5,74.2,39.3C63.6,47.2,45.4,47.1,31.7,48.2C18.1,49.4,9,51.8,-2.3,55.8C-13.6,59.8,-27.3,65.3,-38.1,62.5C-48.9,59.7,-56.8,48.5,-65.7,36.7C-74.5,24.9,-84.4,12.5,-86.1,-1C-87.9,-14.5,-81.7,-29.1,-70.8,-37.3C-59.9,-45.6,-44.3,-47.6,-31.7,-55.5C-19.1,-63.5,-9.5,-77.4,3.3,-83.1C16.2,-88.8,32.3,-86.4,44.2,-78Z;
                                M41.7,-78.3C50.6,-67.1,52,-49,58.7,-34.7C65.5,-20.5,77.6,-10.3,78.5,0.5C79.4,11.3,69,22.5,61.2,35C53.4,47.4,48.2,60.9,38.4,64.2C28.7,67.5,14.3,60.5,2.2,56.7C-9.9,52.8,-19.8,52.1,-30.2,49.2C-40.6,46.3,-51.6,41.2,-60.3,32.6C-69.1,24.1,-75.6,12,-75.6,0C-75.6,-12.1,-69.2,-24.2,-59.9,-31.7C-50.5,-39.2,-38.3,-42.2,-27.8,-52.4C-17.4,-62.7,-8.7,-80.3,3.9,-87C16.4,-93.7,32.8,-89.5,41.7,-78.3Z;
                                M44.2,-78C56,-69.7,63.6,-55.4,70.7,-41.4C77.8,-27.4,84.3,-13.7,86.1,1C87.8,15.7,84.8,31.5,74.2,39.3C63.6,47.2,45.4,47.1,31.7,48.2C18.1,49.4,9,51.8,-2.3,55.8C-13.6,59.8,-27.3,65.3,-38.1,62.5C-48.9,59.7,-56.8,48.5,-65.7,36.7C-74.5,24.9,-84.4,12.5,-86.1,-1C-87.9,-14.5,-81.7,-29.1,-70.8,-37.3C-59.9,-45.6,-44.3,-47.6,-31.7,-55.5C-19.1,-63.5,-9.5,-77.4,3.3,-83.1C16.2,-88.8,32.3,-86.4,44.2,-78Z"></animate>
                            </path>
                        </svg>
                    </div>
                </section>
            </div >
            <Post />
            <div className="links">
                <div className="linkDiv"><Link to={'/'} className='homeEmblem'><FaHome className="homepage-icon" /><span className="link-text">{innerWidth > 576 ? " Home page" : ""}</span></Link></div>
                <div className="linkDiv"><Link to={'/posts/create'} className='createAnswerEmblem'><IoCreate /><span className="link-text">{innerWidth > 576 ? " Create new Post" : ""}</span></Link></div>
                <div className="linkDiv"><Link to={'/signup'} className='signupEmblem'><FaUserTie /><span className="link-text">{innerWidth > 576 ? " Sign up" : ""}</span></Link></div>
            </div>
        </div >
    );
}

export default MainPage;