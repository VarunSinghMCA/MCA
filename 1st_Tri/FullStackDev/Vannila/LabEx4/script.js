const feedbackForm = document.getElementById('feedbackForm');
const feedbackContainer = document.getElementById('feedbackContainer');
const clearBtn = document.getElementById('clearFeedbacks');
const welcomeMessage = document.getElementById('welcomeMessage');
const feedbackTextarea = document.getElementById('feedback');
const charCount = document.getElementById('charCount');

// Show Welcome Back using sessionStorage
if (sessionStorage.getItem('visited')) {
  welcomeMessage.textContent = 'Welcome Back!';
} else {
  sessionStorage.setItem('visited', 'true');
}

// Real-time character count
feedbackTextarea.addEventListener('input', () => {
  const maxLen = 250;
  const currentLen = feedbackTextarea.value.length;
  charCount.textContent = `${currentLen}/${maxLen}`;
});

// Load feedbacks on page load
window.addEventListener('DOMContentLoaded', loadFeedbacks);

// Handle form submission
feedbackForm.addEventListener('submit', function (event) {
  event.preventDefault();

  const name = document.getElementById('name').value.trim();
  const email = document.getElementById('email').value.trim();
  const department = document.getElementById('department').value;
  const rating = document.querySelector('input[name="rating"]:checked');
  const feedbackText = document.getElementById('feedback').value.trim();

  if (!name || !email || !department || !rating || !feedbackText) {
    alert('Please fill out all fields.');
    return;
  }

  const feedback = {
    name,
    email,
    department,
    rating: rating.value,
    feedback: feedbackText,
    timestamp: new Date().toLocaleString(),
  };

  saveFeedback(feedback);
  feedbackForm.reset();
  charCount.textContent = '0/250';
  loadFeedbacks();
  alert(`Thank you for your feedback, ${name}!`);
});

// Save to localStorage
function saveFeedback(feedback) {
  const feedbacks = JSON.parse(localStorage.getItem('feedbacks')) || [];
  feedbacks.push(feedback);
  localStorage.setItem('feedbacks', JSON.stringify(feedbacks));
}

// Load and display feedbacks
function loadFeedbacks() {
  feedbackContainer.innerHTML = '';
  const feedbacks = JSON.parse(localStorage.getItem('feedbacks')) || [];

  if (feedbacks.length === 0) {
    feedbackContainer.innerHTML = '<p class="text-gray-600">No feedback submitted yet.</p>';
    return;
  }

  feedbacks.forEach(f => {
    const card = document.createElement('div');
    card.className = 'bg-white p-4 rounded shadow border-l-4 border-blue-500';

    card.innerHTML = `
      <p class="font-semibold text-lg">${f.name} <span class="text-sm text-gray-500">(${f.email})</span></p>
      <p class="text-sm text-gray-600">Department: ${f.department.toUpperCase()}</p>
      <p class="text-sm text-yellow-600 font-semibold">Rating: ${f.rating}/5</p>
      <p class="text-gray-700 mt-2">${f.feedback}</p>
      <p class="text-xs text-gray-400 mt-1">${f.timestamp}</p>
    `;

    feedbackContainer.appendChild(card);
  });
}

// Clear all feedbacks
clearBtn.addEventListener('click', () => {
  if (confirm('Are you sure you want to delete all feedbacks?')) {
    localStorage.removeItem('feedbacks');
    loadFeedbacks();
  }
});
