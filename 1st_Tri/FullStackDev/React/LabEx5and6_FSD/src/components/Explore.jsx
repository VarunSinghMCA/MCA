import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Explore(){

    const images = [
        "https://plus.unsplash.com/premium_photo-1694699356040-2a14b00b67b8?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&    ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1543352634-99a5d50ae78e?q=80&w=1171&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1494329157681-b2a3c7ff8aed?q=80&w=1632&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
      ];

    const [currentImage, setCurrentImage] = useState(0);

    useEffect(() => {
      const interval = setInterval(() => {
        setCurrentImage((prevIndex) => (prevIndex + 1) % images.length);
      }, 2000); // 1.5 seconds

      return () => clearInterval(interval); // Cleanup on unmount
    }, []);

    const navigate = useNavigate();

    return(
        <div className='h-fit w-full text-white py-20 bg-[#171c2d] flex lg:flex-row flex-col'>
            <div className='lg:flex-[.55] p-20 flex flex-col gap-8'>
                <h1 className='text-4xl font-bold'>
                    Your Culinary Journey Starts Here
                </h1>

                <p className='max-w-[542px] text-lg'>
                    Experience exceptional dining with our carefully crafted menu items. From appetizers to desserts, each dish tells a story of flavor and tradition.
                </p>

                <ul className="list-none space-y-2">
                    <li>✔ Premium quality ingredients</li>
                    <li>✔ Expert chef preparations</li>
                    <li>✔ Customizable dining experience</li>
                </ul>

                <button
                  onClick={() => navigate('/menu')}
                  className='mt-6 bg-[#f9982f] text-white rounded hover:bg-[#e37239] transition-colors h-12 w-48 font-semibold cursor-pointer'
                >
                    Explore Menu
                </button>

            </div>

            <div className='flex-[.45] flex items-center justify-center'>
                <span className='border rounded-lg h-[284px] w-auto overflow-hidden'>
                  <img
                    src={images[currentImage]}
                    alt="Culinary Experience"
                    className='h-full w-full object-cover transition-all duration-500'
                  />
                </span>
            </div>
        </div>
    )
}

export default Explore;