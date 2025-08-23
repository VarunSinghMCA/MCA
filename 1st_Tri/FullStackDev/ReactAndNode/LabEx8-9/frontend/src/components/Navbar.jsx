
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useAuth } from "../store/useAuth";
import { logout } from "../store/reducers/authSlice";

function Navbar({ name }) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { token } = useAuth();

  const handleLogout = () => {
    dispatch(logout());
    navigate("/auth/login");
  };

  return (
    <div className="md:max-w-[80%] m-0 p-0 w-full bg-[#254252] shadow-md md:border border-[#ffffff40] md:rounded-4xl">
      <div className="px-8 py-4 flex justify-between items-center">
        <h1>
          <span
            className="text-2xl font-bold text-[#F9982F] tracking-wide cursor-pointer"
            onClick={() => navigate("/home")}
          >
            {name}
          </span>
        </h1>

        <ul className="flex gap-6 text-white text-lg">
          {[
            { name: "Home", nav: "/home" },
            { name: "Menu", nav: "/menu" },
            { name: "Contact", nav: "/contact" },
          ].map(({ name, nav }, index) => (
            <li key={index}>
              <span
                className="hover:text-[#F9982F] transition cursor-pointer"
                onClick={() => navigate(nav)}
              >
                {name}
              </span>
            </li>
          ))}
          {
              token && 
              <li>
              <span
                className="hover:text-[#F9982F] transition cursor-pointer"
                onClick={() => navigate("/upload")}
              >
                Uploads
              </span>
            </li>
            }

          <li>
            {token ? (
              <span
                className="flex items-center justify-center h-auto w-[30px] transition cursor-pointer text-[#f92f2f] hover:text-white border border-[#f92f2f] bg-[#f92f2f]/50 hover:bg-[#f92f2f] rounded-sm mr-2 font-bold"
                onClick={handleLogout}
              >
                {/* Logout */}
                  {/* <img src="/bin-icon.svg" alt="Logout" className="h-full w-full p-1"/> */}
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 490.3 490.3"
                  className="w-full h-full fill-white transition-colors duration-300 cursor-pointer p-1.5"
                >
                  <g>
                    <g>
                      <path d="M0,121.05v248.2c0,34.2,27.9,62.1,62.1,62.1h200.6c34.2,0,62.1-27.9,62.1-62.1v-40.2c0-6.8-5.5-12.3-12.3-12.3
                        s-12.3,5.5-12.3,12.3v40.2c0,20.7-16.9,37.6-37.6,37.6H62.1c-20.7,0-37.6-16.9-37.6-37.6v-248.2c0-20.7,16.9-37.6,37.6-37.6h200.6
                        c20.7,0,37.6,16.9,37.6,37.6v40.2c0,6.8,5.5,12.3,12.3,12.3s12.3-5.5,12.3-12.3v-40.2c0-34.2-27.9-62.1-62.1-62.1H62.1
                        C27.9,58.95,0,86.75,0,121.05z"/>
                      <path d="M385.4,337.65c2.4,2.4,5.5,3.6,8.7,3.6s6.3-1.2,8.7-3.6l83.9-83.9c4.8-4.8,4.8-12.5,0-17.3l-83.9-83.9
                        c-4.8-4.8-12.5-4.8-17.3,0s-4.8,12.5,0,17.3l63,63H218.6c-6.8,0-12.3,5.5-12.3,12.3c0,6.8,5.5,12.3,12.3,12.3h229.8l-63,63
                        C380.6,325.15,380.6,332.95,385.4,337.65z"/>
                    </g>
                  </g>
                </svg>



              </span>
            ) : (
              <span
                className="transition cursor-pointer border border-transparent bg-[#F9982F]/90 hover:bg-[#F9982F] px-2 pt-1 pb-1.5 rounded-sm mr-2 font-bold"
                onClick={() => navigate("/auth/login")}
              >
                Login
              </span>
            )}

            
          </li>
          
        </ul>
      </div>
    </div>
  );
}

export default Navbar
