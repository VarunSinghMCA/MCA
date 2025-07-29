import { useNavigate } from "react-router-dom"

function Navbar() {

  const navigate = useNavigate();
  
  return (
    <div className="md:max-w-[80%] w-full bg-[#254252] shadow-md border border-[#ffffff40] md:rounded-4xl">
      <div className="px-8 py-4 flex justify-between items-center">
        <h1>
            <span className="text-2xl font-bold text-[#F9982F] tracking-wide cursor-pointer"
                onClick={() => navigate('/home')}
            >
                FoodLicious
            </span>
        </h1>

        <ul className="flex gap-6 text-white text-lg">

          <li>
            <span 
              className="hover:text-[#F9982F] transition cursor-pointer"
              onClick={() => navigate('/home')}
            >
              Home
            </span>
            </li>

            <li>
            <span 
              className="hover:text-[#F9982F] transition cursor-pointer"
              onClick={() => navigate('/menu')}
            >
                Menu
            </span>
            </li>

            {/* <li>
            <span 
              className="hover:text-[#F9982F] transition cursor-pointer"
              onClick={() => navigate('/services')}
            >
                Services
            </span>
            </li>

            <li>
            <span
                className="hover:text-[#F9982F] transition cursor-pointer"
                onClick={() => navigate('/about')}
            >
                About
            </span>
            </li> */}

            <li>
            <span 
              className="hover:text-[#F9982F] transition cursor-pointer"
              onClick={() => navigate('/contact')}
            >
                Contact
            </span>
            </li>
        </ul>
      </div>
    </div>
  )
}

export default Navbar
