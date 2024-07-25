import { useNavigate } from "react-router-dom";
import "../../Pages/MainPage/MainPage.css";
import "./logout.css"

function Logout({ innerWidth }) {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem('jwtToken');
        navigate('/login');
    }

    return (
        <span onClick={handleLogout} className="link-text">{innerWidth > 576 ? " Logout" : ""}</span>
    )
}

export default Logout;