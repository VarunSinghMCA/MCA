import React from 'react';
import { useNavigate } from 'react-router-dom';

const MenuHighlight = () => {

const menuItems = [
    {
        title:"Signature Appetizers",
        description:"Start your meal with our chef&apos;s special appetizers",
        imageLabel:"Appetizer Image",
        category:"Appetizers",
        url: "https://images.unsplash.com/photo-1600891964599-f61ba0e24092?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },{
        title:"Premium Main Courses",
        description:"Indulge in our expertly prepared main dishes",
        imageLabel:"Main Course Image",
        category:"Main Course",
        url: "https://images.unsplash.com/photo-1559847844-5315695dadae?q=80&w=1158&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",

    },{
        title:"Delicious Desserts",
        description:"End your meal with our sweet creations",
        imageLabel:"Dessert Image",
        category:"Desserts",
        url:"https://images.unsplash.com/photo-1509474520651-53cf6a80536f?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

    },
]

    const navigate = useNavigate();


  return (
    <section className="bg-[#1c3a4b] py-12 px-6 text-center text-white">
      <h2 className="text-2xl sm:text-3xl font-bold mb-2">Our Menu Highlights</h2>
      <p className="text-sm text-gray-300 mb-8">Discover our most popular dishes</p>

      <div className="flex flex-col sm:flex-row gap-6 justify-center items-center mb-8">
        {
            menuItems.map((item,index) => ((
                <MenuCard
                  index = {index}
                  title="Signature Appetizers"
                  description="Start your meal with our chef&apos;s special appetizers"
                  imageLabel={item.imageLabel}
                  category={item.category}
                  url={item.url}
                />
            )))
        }
      </div>

      <button onClick={()=> navigate("/menu")} className="bg-[#f9982f] hover:bg-[#e3823f] text-black font-semibold py-2 px-6 rounded-lg transition duration-300 cursor-pointer">
        View Full Menu
      </button>
    </section>
  );
};

const MenuCard = ({ title, description, imageLabel, category, url, index }) => {
  return (
    <div index={index} className="bg-[#171c2d] text-white rounded-lg shadow-md p-4 w-full sm:w-[300px]">
      <div className="bg-[#2c3e50] h-40 rounded-md flex items-center justify-center text-gray-300 mb-4">
        <img src={url} alt={imageLabel} className='rounded-md h-full w-full object-cover'/>
      </div>
      <h3 className="text-lg font-semibold mb-1">{title}</h3>
      <p className="text-sm text-gray-300 mb-3">{description}</p>
      <span className="bg-[#f9982f] text-black text-xs px-3 py-1 rounded-full">{category}</span>
    </div>
  );
};

export default MenuHighlight;
