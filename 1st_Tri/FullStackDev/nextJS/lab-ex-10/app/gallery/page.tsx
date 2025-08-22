'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function GalleryPage() {
  const [query, setQuery] = useState('');
  const router = useRouter();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (query.trim() !== '') {
      router.push(`/gallery/${query}`);
    }
  };

  return (
    <section className="space-y-8">
      {/* Heading */}
      <div className="text-center space-y-2">
        <h1 className="text-3xl font-extrabold text-gray-900">
          Search Unsplash Gallery
        </h1>
        <p className="text-gray-600">
          Type a keyword (e.g., <span className="italic">nature</span>,{' '}
          <span className="italic">cars</span>, <span className="italic">city</span>)
          to explore beautiful images.
        </p>
      </div>

      {/* Search Form */}
      <form
        onSubmit={handleSubmit}
        className="mx-auto flex max-w-xl gap-3 rounded-xl border bg-white p-3 shadow-sm transition focus-within:shadow-md"
      >
        <input
          type="text"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Search images..."
          className="flex-1 rounded-lg px-3 py-2 text-gray-700 placeholder-gray-400 outline-none focus:ring-0"
        />
        <button
          type="submit"
          className="rounded-lg bg-blue-600 px-5 py-2 text-white font-medium hover:bg-blue-700 transition"
        >
          Search
        </button>
      </form>
    </section>
  );
}
