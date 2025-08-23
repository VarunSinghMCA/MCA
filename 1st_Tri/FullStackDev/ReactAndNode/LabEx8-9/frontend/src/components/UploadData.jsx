
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../store/useAuth';


function UploadData() {
  const { token } = useAuth();
  const navigate = useNavigate();
  const [title, setTitle] = useState('');
  const [category, setCategory] = useState('');
  const [description, setDescription] = useState('');
  const [image, setImage] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    if (!token) {
      navigate('/');
    }
  }, [token, navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    setLoading(true);
    try {
      const formData = new FormData();
      formData.append('title', title);
      formData.append('category', category);
      formData.append('description', description);
      if (image) formData.append('image', image);

      await axios.post('http://localhost:5000/api/v1/meals', formData, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      setSuccess('Meal uploaded successfully!');
      setTitle('');
      setCategory('');
      setDescription('');
      setImage(null);
    } catch (err) {
      setError(err.response?.data?.message || 'Upload failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-16 p-8 rounded-2xl shadow-lg backdrop-blur-md bg-white/20 border border-white/30" style={{boxShadow: '0 8px 32px 0 rgba(31, 38, 135, 0.37)'}}>
      <h2 className="text-3xl font-bold mb-6 text-center text-white drop-shadow">Upload Meal</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block mb-1 font-semibold text-white/90">Title</label>
          <input
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            className="w-full border-none rounded px-3 py-2 bg-white/40 text-black focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block mb-1 font-semibold text-white/90">Category</label>
          <input
            type="text"
            value={category}
            onChange={e => setCategory(e.target.value)}
            className="w-full border-none rounded px-3 py-2 bg-white/40 text-black focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block mb-1 font-semibold text-white/90">Description</label>
          <textarea
            value={description}
            onChange={e => setDescription(e.target.value)}
            className="w-full border-none rounded px-3 py-2 bg-white/40 text-black focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
            rows={3}
            required
          />
        </div>
        <div className="mb-6">
          <label className="block mb-1 font-semibold text-white/90">Image</label>
          <input
            type="file"
            accept="image/*"
            onChange={e => setImage(e.target.files[0])}
            className="w-full text-orange-400 hover:text-orange-600 hover:cursor-pointer"
            required
          />
        </div>
        {error && <div className="text-red-300 mb-2 text-center">{error}</div>}
        {success && <div className="text-green-300 mb-2 text-center">{success}</div>}
        <button
          type="submit"
          className="w-full bg-[#f9982f] text-white font-bold py-2 rounded-lg hover:bg-[#e37239] transition shadow-lg"
          disabled={loading}
        >
          {loading ? 'Uploading...' : 'Upload Meal'}
        </button>
      </form>
    </div>
  );
}

export default UploadData;
