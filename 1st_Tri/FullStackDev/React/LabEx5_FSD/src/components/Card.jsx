import React from 'react';

function Card({ title, image, tag, description }) {
  return (
    <div className="bg-[#1e2738] rounded-xl shadow-lg overflow-hidden flex flex-col transition-transform hover:scale-[1.02]">
      <img src={image} alt={title} className="h-48 w-full object-cover" />
      <div className="p-4 flex flex-col gap-2">
        <h2 className="text-xl font-semibold">{title}</h2>
        <span className="text-sm text-[#f9982f] font-medium">{tag}</span>
        <p className="text-sm text-gray-300">{description}</p>
      </div>
    </div>
  );
}

export default Card;
