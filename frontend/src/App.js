import logo from './logo.PNG';
import './App.css';
import MainPage from './Pages/MainPage/MainPage';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h2>Loading....</h2>
        <MainPage></MainPage>
      </header>
    </div>
  );
}

export default App;
