import './App.css';
import MainPage from './Pages/MainPage/MainPage';
import { createContext, useState } from 'react';

export const ThemeContext = createContext(null);

function App() {
  const [theme, setTheme] = useState("light");

  const toggleTheme = () => {
    setTheme(current => current === "light" ? "dark" : "light");
  }
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      <div className="App" id={theme}>
        <button onClick={toggleTheme} className="darkLightBtn">{theme === "light" ? <img className="darkLightImg" alt="moon" src="./Assets/moon.svg"></img> : <img className="darkLightImg" alt="sun" src="./Assets/sun.svg"></img>}</button>
        <MainPage />
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
