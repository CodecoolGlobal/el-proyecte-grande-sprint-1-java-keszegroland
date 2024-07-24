import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import SignUp from './Pages/SignUp/SignUp';
import CreatePost from './Pages/CreatePost/CreatePost';
import Login from './Pages/Login/Login';
import App from './App';

const router = createBrowserRouter(
  [
    {
      children: [
        {
          path: "/",
          element: <App />
        },
        {
          path: "/signup",
          element: <SignUp />
        },
        {
          path: "/posts/create",
          element: <CreatePost />
        },
        {
          path: "/login",
          element: <Login />
        }
      ]
    }
  ]
)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}></RouterProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
