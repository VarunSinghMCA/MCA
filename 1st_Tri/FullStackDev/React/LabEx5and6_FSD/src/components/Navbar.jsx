import { useNavigate } from "react-router-dom"

function Navbar({name}) {

  const navigate = useNavigate();
  
  return (
    <div className="md:max-w-[80%] m-0 p-0 w-full bg-[#254252] shadow-md md:border border-[#ffffff40] md:rounded-4xl">
      <div className="px-8 py-4 flex justify-between items-center">
        <h1>
            <span className="text-2xl font-bold text-[#F9982F] tracking-wide cursor-pointer"
                onClick={() => navigate('/home')}
            >
                {name}
            </span>
        </h1>

        <ul className="flex gap-6 text-white text-lg">

          {
          [
            {
              name: "Home",
              nav: "/home"
            },{
              name: "Menu",
              nav: "/menu"
            },{
              name: "Contact",
              nav: "/contact"
            },
            // { name: "About", nav: "/about" },
            // { name:"Services", nav: "/services"}
          ].map(({name,nav},index)=>(
            <li key={index}>
              <span 
                className="hover:text-[#F9982F] transition cursor-pointer"
                onClick={() => navigate(nav)}
              >
                {name}
              </span>
            </li>
          ))
          }
          
        </ul>
      </div>
    </div>
  )
}

export default Navbar
