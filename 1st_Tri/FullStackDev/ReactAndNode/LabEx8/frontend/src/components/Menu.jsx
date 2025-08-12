import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from './Card';

function Menu() {
  const [meals, setMeals] = useState([]);
  const [loading, setLoading] = useState(true);

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;

  useEffect(() => {
    const fetchMeals = async () => {
      try {
        const response = await axios.get('http://localhost:5000/api/v1/meals');
        console.log(response.data);
        setMeals(response.data || []); // backend sends { meals: [...] }
      } catch (error) {
        console.error('Failed to fetch meals:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchMeals();
  }, []);

  // Pagination calculation
  const totalPages = Math.ceil(meals.length / itemsPerPage);
  const startIdx = (currentPage - 1) * itemsPerPage;
  const currentMeals = meals.slice(startIdx, startIdx + itemsPerPage);

  return (
    <div className="min-h-dvh px-6 py-12 bg-gradient-to-br from-[#254252] to-[#171c2d] text-white">
      <h1 className="text-4xl font-bold mb-10 text-center">
        Explore Our Delicious Menu 🍽️
      </h1>

      {loading ? (
        <div className="flex justify-center items-center h-64 text-xl animate-pulse">
          Loading meals...
        </div>
      ) : (
        <>
          <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {currentMeals.map((meal,index) => (
              <Card
                key={index  || meal.id} // use index as fallback if id is not available
                title={meal.title}
                image={meal.image}
                tag={meal.category} // show category tag
                description={
                  meal.description?.length > 100
                    ? meal.description.slice(0, 100) + '...'
                    : meal.description
                }
              />
            ))}
          </div>

          {/* Pagination Controls */}
          {totalPages > 1 && (
            <div className="flex justify-center mt-10 gap-4">
              <button
                disabled={currentPage === 1}
                onClick={() => setCurrentPage((prev) => prev - 1)}
                className={`px-4 py-2 rounded ${
                  currentPage === 1
                    ? 'bg-gray-600 cursor-not-allowed'
                    : 'bg-[#f9982f] hover:bg-[#e37239]'
                }`}
              >
                Previous
              </button>

              <span className="text-lg font-semibold text-center">
                {currentPage} of {totalPages}
              </span>

              <button
                disabled={currentPage === totalPages}
                onClick={() => setCurrentPage((prev) => prev + 1)}
                className={`px-4 py-2 rounded ${
                  currentPage === totalPages
                    ? 'bg-gray-600 cursor-not-allowed'
                    : 'bg-[#f9982f] hover:bg-[#e37239]'
                }`}
              >
                Next
              </button>
            </div>
          )}
        </>
      )}
    </div>
  );
}

export default Menu;
