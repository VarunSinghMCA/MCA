import axios from 'axios';
import './App.css';
import React, { useEffect, useState } from 'react';

export default function App() {
  const [data, setData] = useState([]);
  const [inputData, setInputData] = useState('');
  const [searchData, setSearchData] = useState('nature');
  const [loading, setLoading] = useState(true);

  const UNSPLASH_ACCESS_KEY = '4rI8_Q227Cv012WZ2dLSU1jy3cyrJS9Zlmp9OuIiES8'; // 🔁 Replace with your key

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const res = await axios.get('http://api.unsplash.com/search/photos', {
          headers: {
            Authorization: `Client-ID ${UNSPLASH_ACCESS_KEY}`
          },
          params: {
            query: searchData,
            // per_page: 12
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
    <div className='h-[100dvh] w-[100dvw] bg-gray-100 dark:bg-gray-900 text-black dark:text-white'>
      {loading ? (
        <div className='flex h-full items-center justify-center'>
          <h1 className='text-2xl font-bold'>Loading...</h1>
        </div>
      ) : (
        <div>
          <div className='flex items-center justify-center flex-col gap-4 mt-4'>
            <input 
              type="text" 
              value={inputData} 
              onChange={e => setInputData(e.target.value)}
              placeholder='Search Unsplash...'
              className='border border-gray-300 rounded-md px-4 py-2 w-1/3'
            />
            <button
              onClick={() => {
                if (inputData.trim()) {
                  setSearchData(inputData);
                  setInputData('');
                }
              }}
              className='bg-blue-500 text-white px-4 py-2 rounded-md'
            >
              Search
            </button>
          </div>
          <div className='grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4 p-4'>
            {data.map((item) => (
              <ImageBox 
                key={item.id} 
                src={item.urls.small} 
                alt={item.alt_description || 'Unsplash Image'} 
              />
            ))}
          </div>
        </div>
      )}
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
