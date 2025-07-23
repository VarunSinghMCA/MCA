const feedbackForm = document.getElementById('feedbackForm');

feedbackForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const department = document.getElementById('department').value;
    const rating = document.querySelector('input[name="rating"]:checked');
    const feedback = document.getElementById('feedback').value;

    if (name && email && department && rating && feedback) {
        alert(`Thank you for your feedback, ${name}!`);
        feedbackForm.reset();
    } else {
        alert('Please fill out all fields.');
    }
});
