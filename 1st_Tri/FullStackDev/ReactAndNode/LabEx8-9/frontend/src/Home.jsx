import { useNavigate } from 'react-router-dom';
// import { useEffect, useState } from 'react';
import Explore from './components/Explore';
import MenuHighlight from './components/MenuHighlight'
import FAQ from './components/FAQ';

function Home() {
  return (
    <div className='h-fit w-full m-0 p-0'>
      <Hero/>
      <Explore/>
      <MenuHighlight/>
      <FAQ/>
    </div>
  )
}





function Hero() {

  const navigate = useNavigate();

  return (
    <div className='min-h-[536px] text-white w-full flex items-center justify-center flex-col gap-6 bg-gradient-to-br from-[#254252] to-[#171c2d] text-center'>
      <h1 className='text-5xl'>
        Welcome to <i className='text-[#f9982f]'>FoodLicious</i>
      </h1>
      <p className='w-[452px] '>
        Discover amazing flavors and book your perfect dining experience with our curated menu selection
      </p>
      <div className='px-8 py-4 bg-[#171c2d80]  mb-4 rounded-lg'>
        <p>
            Featured Today
        </p>
        <p className='text-[#f9982f]'>
            Premium Food Experience
        </p>
      </div>


      <button
          onClick={() => navigate('/menu')}
          className=' bg-[#f9982f] text-black rounded hover:bg-[#e37239] transition-colors h-12 w-48 font-semibold cursor-pointer'
        >
            Get Started
        </button>
    </div>
  )
}



export default Home
