import Link from 'next/link';

export default function HomePage() {
  return (
    <section className="space-y-12">
      {/* Hero Section */}
      <div className="text-center space-y-4">
        <h1 className="text-4xl sm:text-5xl font-extrabold text-gray-900">
          Welcome to LabEx10
        </h1>
        <p className="text-lg text-gray-600 max-w-2xl mx-auto">
          A clean and minimal <span className="font-semibold">Next.js</span> app with{' '}
          <span className="font-semibold">TypeScript</span>,{' '}
          <span className="font-semibold">Tailwind CSS</span>, navigation, and Unsplash-powered images.
        </p>
      </div>

      {/* Cards */}
      <div className="grid gap-8 sm:grid-cols-2">
        <div className="group rounded-2xl bg-white border p-8 shadow-sm transition hover:shadow-md hover:-translate-y-1">
          <h2 className="mb-3 text-2xl font-semibold text-gray-800">
            🚀 Get started
          </h2>
          <p className="text-gray-600">
            Visit the{' '}
            <Link href="/about" className="text-blue-600 font-medium hover:underline">
              About
            </Link>{' '}
            page to learn more or check the{' '}
            <Link href="/gallery" className="text-blue-600 font-medium hover:underline">
              Gallery
            </Link>{' '}
            for dynamic Unsplash images.
          </p>
        </div>

        <div className="overflow-hidden rounded-2xl border shadow-sm">
          <img
            src="https://images.unsplash.com/photo-1600891964599-f61ba0e24092?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            alt="Hero"
            width={800}
            height={500}
            className="h-full w-full object-cover transition duration-300 hover:scale-105"
          />
        </div>
      </div>
    </section>
  );
}
