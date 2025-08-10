import React, { useState } from "react";

const faqs = [
  {
    question: "How do I make a reservation?",
    answer:
      "Simply browse our menu, select your desired items, and use our contact form to book your dining experience. We'll confirm your reservation within 24 hours.",
  },
  {
    question: "What dietary restrictions do you accommodate?",
    answer:
      "We accommodate various dietary needs including vegetarian, vegan, gluten-free, and allergies. Please mention your requirements while booking.",
  },
  {
    question: "Can I modify my booking?",
    answer:
      "Yes, you can modify your booking by contacting us at least 6 hours prior to your reservation time.",
  },
  {
    question: "What are your operating hours?",
    answer:
      "We are open from 10 AM to 11 PM every day, including weekends and public holidays.",
  },
  {
    question: "Do you offer group dining packages?",
    answer:
      "Absolutely! We offer special packages for groups and events. Contact us to learn more about our group dining offers.",
  },
];

const FAQ = () => {
  const [openIndex, setOpenIndex] = useState(null);

  const toggleFAQ = (index) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  return (
    <section className="min-h-fit h-[700px] px-4 py-12 bg-[#0f172a] text-white flex flex-col items-center">
      <h2 className="text-3xl sm:text-4xl font-bold text-center mb-2">
        Frequently Asked Questions
      </h2>
      <p className="text-gray-300 text-center mb-10">
        Everything you need to know about our service
      </p>

      <div className="w-full max-w-3xl flex flex-col gap-4 ">
        {faqs.map((faq, index) => (
          <div
            key={index}
            className={`transition-all duration-300 rounded-md bg-[#1e3a48] overflow-hidden ${
              openIndex === index ? "pb-4" : ""
            }`}
          >
            <button
              className="w-full h-auto  flex justify-between items-center text-left px-6 py-4 font-medium text-white hover:bg-[#264b5e] transition"
              onClick={() => toggleFAQ(index)}
            >
              <span>{faq.question}</span>
              <span
                className={`text-orange-400 transform transition-transform duration-300 text-xl w-[20px] h-auto ${
                  openIndex === index ? "rotate-180" : ""
                }`}
              >
                  <img src="/arrow-down-white.svg" alt="⇩" className="w-full h-full"/>
              </span>
            </button>
            <div
              className={`px-6 text-gray-200 transition-all duration-300 ease-in-out ${
                openIndex === index ? "max-h-40 opacity-100" : "max-h-0 opacity-0"
              } overflow-hidden`}
            >
              <p className="mt-1">{faq.answer}</p>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

export default FAQ;
