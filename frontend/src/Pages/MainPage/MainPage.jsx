import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import MainPageLogo from "../../Components/MainPageComponents/MainPageLogo";
import MainPageSideButtons from "../../Components/MainPageComponents/MainPageSideButtons";
import Post from "../../Components/Post/Post";
import "./MainPage.css";
import { useAuth } from "../../AuthProvider";

function MainPage() {
  const [innerWidth, setInnerWidth] = useState(window.innerWidth);
  const navigate = useNavigate();
  const { logout } = useAuth();

  useEffect(() => {
    const handleResize = () => setInnerWidth(window.innerWidth)

    window.addEventListener('resize', handleResize)
    return () => {
      window.removeEventListener('resize', handleResize)
    };
  }, [])

  const handleLogout = () => {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('user')
    logout()
    navigate('/login');
  }

  return (
    <div className="MainPage">
      <div className="watermark">
        <MainPageLogo />
      </div >
      <Post />
      <MainPageSideButtons onLogout={handleLogout} innerWidth={innerWidth} setInnerWidth={setInnerWidth} />
    </div >
  );
}

export default MainPage;