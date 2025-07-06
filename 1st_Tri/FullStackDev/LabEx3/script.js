document.getElementById('menu-btn').addEventListener('click', () => {
      document.getElementById('mobile-menu').classList.toggle('hidden');
    });

    async function loadPlaces() {
      const container = document.getElementById('places-list');
      container.innerHTML = '<div class="col-span-3 text-center text-gray-500">Loading...</div>';
      try {
        const res = await fetch('./data/place.json');
        const data = await res.json();
        container.innerHTML = data.map(r => `
          <div class="bg-white rounded-lg shadow-md p-4 flex flex-col items-center">
            <img src="${r.image}" alt="${r.name}" class="rounded-full w-auto h-[240px] mb-2"/>
            <h3 class="text-xl font-semibold mb-1">${r.name}</h3>
            <p class="text-gray-600">${r.cuisine}</p>
          </div>`).join('');
      } catch {
        container.innerHTML = '<div class="col-span-3 text-center text-red-500">Failed to load restaurants.</div>';
      }
    }
    loadPlaces();

    document.querySelector('form').addEventListener('submit', e => {
      e.preventDefault();
      alert('Thanks! We will contact you soon.');
    });

    // Gallery Slider Script
    const galleryImages = [
      "images/f1.svg",
      "images/f2.svg",
      "images/f3.svg",
      "images/f4.svg",
      "images/f5.svg",
      "images/f6.svg",
      "images/f7.svg",
    ];



    let galleryIndex = 0;

    const galleryImg = document.getElementById('gallery-image');
    const prevBtn = document.getElementById('gallery-prev');
    const nextBtn = document.getElementById('gallery-next');
    const dotsContainer = document.getElementById('gallery-dots');

    function updateGallery() {
      galleryImg.src = galleryImages[galleryIndex];
      // Update dots
      dotsContainer.innerHTML = galleryImages.map((_, i) =>
        `<button class="w-3 h-3 rounded-full ${i === galleryIndex ? 'bg-green-500' : 'bg-gray-300'}" style="outline:none;border:none;margin:0 2px;" onclick="setGalleryIndex(${i})"></button>`
      ).join('');
    }

    function setGalleryIndex(i) {
      galleryIndex = i;
      updateGallery();
    }

    setInterval(() => {
      galleryIndex = (galleryIndex + 1) % galleryImages.length;
      updateGallery();
    }, 2000);

    prevBtn.onclick = () => {
      galleryIndex = (galleryIndex - 1 + galleryImages.length) % galleryImages.length;
      updateGallery();
    };
    nextBtn.onclick = () => {
      galleryIndex = (galleryIndex + 1) % galleryImages.length;
      updateGallery();
    };

    // Expose setGalleryIndex to global for inline onclick
    window.setGalleryIndex = setGalleryIndex;

    updateGallery();