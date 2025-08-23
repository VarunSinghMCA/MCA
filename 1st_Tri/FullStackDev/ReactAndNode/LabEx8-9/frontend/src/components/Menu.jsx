import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from './Card';

function Menu() {
  const [meals, setMeals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [search, setSearch] = useState('');

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 8;

  useEffect(() => {
    const fetchMeals = async () => {
      try {
        const response = await axios.get('http://localhost:5000/api/v1/meals');
        // Fix: meals are inside response.data.data
        const data = Array.isArray(response.data)
          ? response.data
          : response.data.data || [];
        setMeals(data);
      } catch (error) {
        setError('Failed to fetch meals.');
        console.error('Failed to fetch meals:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchMeals();
  }, []);

  // Filter meals by search
  const filteredMeals = meals.filter(
    (meal) =>
      meal.title?.toLowerCase().includes(search.toLowerCase()) ||
      meal.category?.toLowerCase().includes(search.toLowerCase())
  );

  // Pagination calculation
  const totalPages = Math.ceil(filteredMeals.length / itemsPerPage);
  const startIdx = (currentPage - 1) * itemsPerPage;
  const currentMeals = filteredMeals.slice(startIdx, startIdx + itemsPerPage);

  useEffect(() => {
    // Reset to first page if search changes
    setCurrentPage(1);
  }, [search]);

  return (
    <div className="min-h-dvh px-6 py-12 bg-gradient-to-br from-[#254252] to-[#171c2d] text-white">
      <h1 className="text-4xl font-bold mb-10 text-center">
        Explore Our Delicious Menu 🍽️
      </h1>

      <div className="flex justify-center mb-8">
        <input
          type="text"
          placeholder="Search by name or category..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="px-4 py-2 rounded w-full max-w-md text-white border border-white/20 bg-black/20 focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
        />
      </div>

      {loading ? (
        <div className="flex justify-center items-center h-64 text-xl animate-pulse">
          Loading meals...
        </div>
      ) : error ? (
        <div className="flex justify-center items-center h-64 text-xl text-red-400">
          {error}
        </div>
      ) : (
        <>
          <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {currentMeals.length === 0 ? (
              <div className="col-span-full text-center text-lg">
                No meals found.
              </div>
            ) : (
              currentMeals.map((meal) => (
                <Card
                  key={meal.id || meal._id || meal.title}
                  title={meal.title}
                  image={meal.image}
                  tag={meal.category}
                  description={
                    meal.description?.length > 100
                      ? meal.description.slice(0, 100) + '...'
                      : meal.description
                  }
                />
              ))
            )}
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
