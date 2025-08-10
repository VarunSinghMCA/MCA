import axios from 'axios';
import './App.css';
import React, { useEffect, useState } from 'react';

export default function App() {
  const [data, setData] = useState([]);
  const [inputData, setInputData] = useState('');
  const [searchData, setSearchData] = useState('nature');
  const [loading, setLoading] = useState(true);

  const UNSPLASH_ACCESS_KEY = import.meta.env.VITE_UNSPLASH_ACCESS_KEY;
  console.log(UNSPLASH_ACCESS_KEY);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const res = await axios.get('https://api.unsplash.com/search/photos', {
          headers: {
            Authorization: `Client-ID ${UNSPLASH_ACCESS_KEY}`
          },
          params: {
            query: searchData,
            per_page: 12
          }
        });
        setData(res.data.results);
      } catch (error) {
        console.error('Error fetching data from Unsplash:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [searchData]);

  return (
    <div className="min-h-[100dvh] w-full bg-gray-100 dark:bg-gray-900 text-black dark:text-white">
      <div className="h-auto w-full flex flex-col items-center justify-center gap-4 pt-8">
        
        <label htmlFor="serach" className='h-[102px] w-[50%] flex justify-center flex-col '>
          <input
          type="text"
          id='serach'
          value={inputData}
          onChange={(e) => setInputData(e.target.value)}
          placeholder="Search Unsplash..."
          className="border border-gray-300 rounded-full w-full h-[50px] px-6"
        />
        {
          searchData && 
          (
            <p className='text-gray-400 px-4'>Serched Data: {searchData}</p>
          )
        }
        
        </label>
        
        <button
          onClick={() => {
            if (inputData.trim()) {
              setSearchData(inputData);
              setInputData('');
            }
          }}
          className="bg-blue-500 text-white px-4 py-2 rounded-md cursor-pointer"
        >
          Search
        </button>
      </div>

      <div className="p-6">
        {loading ? (
          <div className="flex h-64 items-center justify-center">
            <h1 className="text-2xl font-bold">Loading...</h1>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {data.map((item) => (
              <ImageBox
                key={item.id}
                src={item.urls.small}
                alt={item.alt_description || 'Unsplash Image'}
              />
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

function ImageBox({ src, alt }) {
  return (
    <div className="w-full overflow-hidden rounded-xl shadow-lg bg-white dark:bg-gray-800">
      <img
        src={src}
        alt={alt}
        className="w-full h-64 object-cover transition-transform duration-300 hover:scale-105"
      />
    </div>
  );
}
