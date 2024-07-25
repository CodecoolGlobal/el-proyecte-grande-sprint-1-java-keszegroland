import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthProvider"

export const ProtectedRoute = ({ children, role }) => {
    const { user } = useAuth();
    console.log(user);
    if (!user) {
        return <Navigate to={"/login"} />
    }

    if (!user.roles.includes(role)) {
        return <Navigate to={"/"} />
    }
    return children;
}