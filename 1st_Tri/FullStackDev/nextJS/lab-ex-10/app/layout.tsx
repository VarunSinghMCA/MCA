import type { Metadata } from 'next';
import './globals.css';
import Navbar from '../components/Navbar';

export const metadata: Metadata = {
  title: 'LabEx10',
  description: 'Simple Next.js + TypeScript + Tailwind demo',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className="min-h-screen bg-white text-gray-900 antialiased">
        <Navbar />

        <main className="mx-auto max-w-6xl px-6 py-12">{children}</main>

        <footer className="mt-20 border-t bg-white py-8">
          <div className="mx-auto flex max-w-6xl flex-col items-center justify-between gap-4 text-center sm:flex-row sm:gap-0">
            <p className="text-sm text-gray-500">
              © {new Date().getFullYear()} <span className="font-semibold">LabEx10</span>. All rights reserved.
            </p>
            <div className="flex gap-4 text-gray-400">
              <a href="https://github.com" target="_blank" className="hover:text-gray-600 transition">
                GitHub
              </a>
              <a href="https://unsplash.com" target="_blank" className="hover:text-gray-600 transition">
                Unsplash
              </a>
            </div>
          </div>
        </footer>
      </body>
    </html>
  );
}
