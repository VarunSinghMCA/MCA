import { useState } from 'react';

const Contact = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    menuItem: '',
    message: '',
  });

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Submitted:', formData);
    alert("Thanks for reaching out!");
    setFormData({ name: '', email: '', menuItem: '', message: '' });
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-[#254252] to-[#171c2d] text-white flex flex-col lg:flex-row items-center justify-center px-4 py-8">
      
      {/* Left Image Section */}
      <div className="w-full lg:w-2/5 mb-10 lg:mb-0 flex justify-center">
        <img
          src="https://plus.unsplash.com/premium_photo-1695758787784-d85c1e00ac82?q=80&w=717&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
          alt="Delicious Food"
          className="rounded-2xl shadow-lg w-full max-w-md object-cover"
        />
      </div>

      {/* Right Form Section */}
      <form
        onSubmit={handleSubmit}
        className="w-full lg:w-1/2 max-w-xl bg-[#171c2d] px-8 py-12 rounded-2xl shadow-xl border border-[#254252] space-y-6 "
      >
        <h2 className="text-3xl font-bold text-[#f9982f]">Get in Touch With Us</h2>
        <p className="text-gray-300">Fill out the form and we’ll get back to you shortly.</p>

        <div className="space-y-4">
          <input
            type="text"
            name="name"
            value={formData.name}
            placeholder="Your Name"
            onChange={handleChange}
            required
            className="w-full px-4 py-3 rounded-md bg-[#254252] text-white placeholder-gray-300 focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
          />
          <input
            type="email"
            name="email"
            value={formData.email}
            placeholder="Your Email"
            onChange={handleChange}
            required
            className="w-full px-4 py-3 rounded-md bg-[#254252] text-white placeholder-gray-300 focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
          />
          <input
            type="text"
            name="menuItem"
            value={formData.menuItem}
            placeholder="Preferred Menu Item (optional)"
            onChange={handleChange}
            className="w-full px-4 py-3 rounded-md bg-[#254252] text-white placeholder-gray-300 focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
          />
          <textarea
            name="message"
            value={formData.message}
            placeholder="Your Message"
            onChange={handleChange}
            rows="5"
            required
            className="w-full px-4 py-3 rounded-md bg-[#254252] text-white placeholder-gray-300 focus:outline-none focus:ring-2 focus:ring-[#f9982f]"
          />
        </div>

        <button
          type="submit"
          className="bg-[#f9982f] hover:bg-[#e37239] text-black font-semibold py-3 px-6 rounded-lg transition duration-300 w-full"
        >
          Send Message
        </button>
      </form>
    </div>
  );
};

export default Contact;
