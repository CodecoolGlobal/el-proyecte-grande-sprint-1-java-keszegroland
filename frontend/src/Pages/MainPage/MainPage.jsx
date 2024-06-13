import { Link } from "react-router-dom";
import Post from "../../Components/Post/Post";
import "./MainPage.css";
import { FaHome, FaUserTie } from "react-icons/fa";
import { IoCreate } from "react-icons/io5";

function MainPage() {
    return (
        <div className="MainPage">
            <div className="navbar">
                <Link to={'/'} className='homeEmblem'><FaHome /></Link>
                <Link to={'/signup'} className='signupEmblem'><FaUserTie /></Link>
                <Link to={'/posts/create'} className='createAnswerEmblem'><IoCreate /></Link>
            </div>
            <Post />
        </div>
    );
}

export default MainPage;