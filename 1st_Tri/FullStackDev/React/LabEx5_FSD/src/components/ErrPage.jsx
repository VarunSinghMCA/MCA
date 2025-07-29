import { useNavigate } from "react-router-dom";

function ErrPage() {

  const navigate = useNavigate();

  return (
    <div className='flex flex-col items-center gap-4 text-white justify-center h-screen bg-black'>
        <h1 className='text-6xl font-bold text-orange-500'>404 - Page Not Found</h1>
        {/* <p className='text-gray-600 mt-4'>The page you are looking for does not exist.</p> */}
        
        {/* <p className='text-gray-500 mt-2'>Or try searching for what you need.</p>
        <input
          type="text"
          placeholder="Search..."
          className="mt-2 px-4 py-2 border border-gray-300 rounded w-1/3"
            onKeyDown={(e) => {
                if (e.key === 'Enter') {
                // Implement search functionality here
                console.log('Searching for:', e.target.value);
                }
            }}
        /> */}
        <div className="text-center m-12">
            <p className='text-gray-500 mt-2'>If you think this is an error, please contact support.</p>
            <p className='text-gray-500 mt-2'>© {new Date().getFullYear()} Foodlicious </p>
            <p className='text-gray-500 mt-2'>All rights reserved.</p>
        </div>

        <button
          onClick={() => navigate('/')}
          className='mt-6 bg-gray-100 text-black rounded hover:bg-gray-300 transition-colors h-12 w-48 font-semibold cursor-pointer'
        >
            Go to Home
        </button>
    </div>
  )
}

export default ErrPage;