import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import SignUp from './Pages/SignUp/SignUp';
import CreatePost from './Pages/CreatePost/CreatePost';
import Login from './Pages/Login/Login';
import App from './App';
import AdminDashboard from './Pages/AdminDashboard/AdminDashboard';
import { AuthProvider } from './AuthProvider';
import { ProtectedRoute } from './ProtectedRoute';

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
        },
        {
          path: "/admin-dashboard",
          element: (
            < ProtectedRoute role={"ROLE_ADMIN"}>
              < AdminDashboard />
            </ProtectedRoute >
          )

        }
      ]
    }
  ]
)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <AuthProvider >
      <RouterProvider router={router}></RouterProvider>
    </AuthProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
