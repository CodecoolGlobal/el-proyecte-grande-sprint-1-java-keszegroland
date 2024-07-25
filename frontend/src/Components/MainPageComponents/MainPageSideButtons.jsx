import { FaHome, FaUserTie } from "react-icons/fa";
import { TbLogout } from "react-icons/tb";
import { IoCreate } from "react-icons/io5";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import "./mainpageSideButtons.css"
import { useGetToken } from "../CustomHook/CustomHook";

export default function MainPageSideButtons({ onLogout, innerWidth, setInnerWidth }) {
    const token = useGetToken();

    useEffect(() => {
        const handleResize = () => setInnerWidth(window.innerWidth)

        window.addEventListener('resize', handleResize)
        return () => {
            window.removeEventListener('resize', handleResize)
        };
    }, [])


    return (
        <div className="links">
            <div className="linkDiv"><Link to={'/'} className='homeEmblem'><FaHome className="homepage-icon" /><span className="link-text">{innerWidth > 576 ? " Home page" : ""}</span></Link></div>
            <div className="linkDiv"><Link to={'/posts/create'} className='createAnswerEmblem'><IoCreate /><span className="link-text">{innerWidth > 576 ? " Create new Post" : ""}</span></Link></div>
            {token ? <></> : <div className="linkDiv"><Link to={'/signup'} className='signupEmblem'><FaUserTie /><span className="link-text">{innerWidth > 576 ? " Sign up" : ""}</span></Link></div>}
            <div className="linkDiv logOutBtn"><Link onClick={onLogout} className='logoutEmblem'><TbLogout /><span className="link-text">{innerWidth > 576 ? " Logout" : ""}</span></Link></div>
        </div>
    )
}