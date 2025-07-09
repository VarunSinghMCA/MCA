// Responsive navigation
const menuToggle = document.querySelector('.menu-toggle');
const navLinks = document.querySelector('.nav-links');

menuToggle.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

// Geolocation API
const locationData = document.getElementById('location-data');
const getLocationBtn = document.getElementById('get-location');


getLocationBtn.addEventListener('click', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            position => {
                console.log(position);
                // Displaying latitude and longitude
                const { latitude, longitude } = position.coords;
                locationData.innerHTML = `
                    <p>Latitude: ${latitude.toFixed(6)}</p>
                    <p>Longitude: ${longitude.toFixed(6)}</p>
                `;
                
            },
            error => {
                locationData.textContent = `Error: ${error.message}`;
            }
        );
        
    } else {
        locationData.textContent = "Geolocation is not supported by your browser";
    }
});

// LocalStorage for feedback
const feedbackForm = document.getElementById('feedback-form');
const storedFeedback = document.getElementById('stored-feedback');

function loadFeedback() {
    const feedback = JSON.parse(localStorage.getItem('webFeedback')) || [];
    storedFeedback.innerHTML = feedback.map(item => `
        <div class="feedback-item">
            <strong>${item.name}:</strong> ${item.comment}
        </div>
    `).join('');
}

feedbackForm.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const name = window.document.getElementById('name').value;
    const comment = document.getElementById('comment').value;
    
    const feedback = JSON.parse(localStorage.getItem('webFeedback')) || [];
    feedback.push({ name, comment });
    localStorage.setItem('webFeedback', JSON.stringify(feedback));
    
    feedbackForm.reset();
    loadFeedback();
});

// Initialize
loadFeedback();
