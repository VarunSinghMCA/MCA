export default function AboutPage() {
  return (
    <section className="max-w-3xl mx-auto space-y-6">
      <h1 className="text-3xl font-bold tracking-tight">About</h1>
      <p className="text-lg text-gray-700">
        LabEx10 is a minimal Next.js + TypeScript + Tailwind CSS example showing basic routing,
        navigation, and image optimization with <code className="bg-gray-100 px-1 rounded">next/image</code>.
      </p>
      <ul className="list-disc list-inside space-y-2 text-gray-600">
        <li>Built with the App Router</li>
        <li>Styled using Tailwind utility classes</li>
        <li>Includes a top navigation bar with active link state</li>
      </ul>
    </section>
  );
}
