import './App.css';
import { useGetToken } from './Components/CustomHook/CustomHook';
import MainPage from './Pages/MainPage/MainPage';
import { createContext, useState } from 'react';
import { Navigate } from 'react-router-dom';


export const ThemeContext = createContext(null);

function App() {
  const [theme, setTheme] = useState("light");
  const token = useGetToken();

  const toggleTheme = () => {
    setTheme(current => current === "light" ? "dark" : "light");
  }
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      <div className="App" id={theme}>
        <button onClick={toggleTheme} className="darkLightBtn">{theme === "light" ? <img className="darkLightImg" alt="moon" src="./Assets/moon.svg"></img> : <img className="darkLightImg" alt="sun" src="./Assets/sun.svg"></img>}</button>
        {token ? (<MainPage />) : (<Navigate to={"/login"} />)}
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
