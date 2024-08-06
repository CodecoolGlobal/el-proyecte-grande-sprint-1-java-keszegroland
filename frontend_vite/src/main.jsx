import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import SignUp from './Pages/SignUp/SignUp';
import CreatePost from './Pages/CreatePost/CreatePost';
import Login from './Pages/Login/Login';
import App from './App.jsx';
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

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider >
      <RouterProvider router={router}></RouterProvider>
    </AuthProvider>
  </React.StrictMode>,
)

