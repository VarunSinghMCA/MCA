'use client';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Image from 'next/image';
import { useParams } from 'next/navigation';

interface UnsplashImage {
  id: string;
  alt_description: string;
  urls: {
    small: string;
    regular: string;
  };
  user: {
    name: string;
    links: {
      html: string;
    };
  };
}

export default function GallerySearchPage() {
  const { search } = useParams<{ search: string }>();
  const [data, setData] = useState<UnsplashImage[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!search) return;

    const fetchData = async () => {
      setLoading(true);
      try {
        const res = await axios.get('https://api.unsplash.com/search/photos', {
          headers: {
            Authorization: `Client-ID ${process.env.NEXT_PUBLIC_UNSPLASH_ACCESS_KEY}`,
          },
          params: {
            query: search,
            per_page: 12,
          },
        });
        setData(res.data.results);
      } catch (error) {
        console.error('Error fetching data from Unsplash:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [search]);

  if (loading) {
    return (
      <p className="text-center text-lg text-gray-600">
        Loading images for "<span className="font-medium">{search}</span>"...
      </p>
    );
  }

  if (data.length === 0) {
    return (
      <p className="text-center text-lg text-gray-600">
        No images found for "<span className="font-medium">{search}</span>"
      </p>
    );
  }

  return (
    <section className="space-y-8">
      {/* Heading */}
      <div className="text-center space-y-2">
        <h1 className="text-3xl font-bold text-gray-900 capitalize">
          Results for "{search}"
        </h1>
        <p className="text-gray-600">
          Found <span className="font-medium">{data.length}</span> photos
        </p>
      </div>

      {/* Image Grid */}
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
        {data.map((img) => (
          <div
            key={img.id}
            className="group relative overflow-hidden rounded-2xl border bg-white shadow-sm transition hover:shadow-md"
          >
            <Image
              src={img.urls.small}
              alt={img.alt_description || 'Unsplash Image'}
              width={400}
              height={300}
              className="h-60 w-full object-cover transition duration-300 group-hover:scale-105"
            />
            {/* Overlay */}
            <div className="absolute inset-0 bg-black/40 opacity-0 group-hover:opacity-100 transition flex items-end p-3">
              <a
                href={img.user.links.html}
                target="_blank"
                rel="noopener noreferrer"
                className="text-white text-sm"
              >
                Photo by <span className="font-semibold">{img.user.name}</span>
              </a>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}
