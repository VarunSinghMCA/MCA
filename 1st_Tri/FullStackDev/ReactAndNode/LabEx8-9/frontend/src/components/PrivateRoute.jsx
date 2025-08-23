import { Navigate, Outlet } from "react-router-dom";

// Checks for token in localStorage (or sessionStorage)
const isAuthenticated = () => {
	return Boolean(localStorage.getItem("token"));
};

const PrivateRoute = () => {
	return isAuthenticated() ? <Outlet /> : <Navigate to="/auth/login" replace />;
};

export default PrivateRoute;
