# FoodLicious Full Stack App

A full stack food menu and upload app using React, Redux Toolkit, Node.js, Express, Prisma, and PostgreSQL. Features authentication, protected routes, file uploads (Cloudinary), and email notifications (Nodemailer).

## Features
- User registration and login (JWT-based authentication)
- Protected routes (frontend and backend)
- Upload meals with title, category, description, and image
- Meals listing with search, pagination, and filtering
- Email notification on successful registration
- Modern UI with glassmorphism effects

## Tech Stack
- **Frontend:** React, Redux Toolkit, Axios, Tailwind CSS
- **Backend:** Node.js, Express, Prisma ORM
- **Database:** PostgreSQL
- **File Uploads:** Multer, Cloudinary
- **Email:** Nodemailer (Gmail)

## Getting Started

### Prerequisites
- Node.js (v18+ recommended)
- npm or yarn
- PostgreSQL database
- Cloudinary account
- Gmail account (for email notifications)

### Environment Variables
Create a `.env` file in `backend/` with:
```
DATABASE_URL=your_postgres_connection_string
JWT_SECRET=your_jwt_secret
CLOUDINARY_CLOUD_NAME=your_cloudinary_cloud_name
CLOUDINARY_API_KEY=your_cloudinary_api_key
CLOUDINARY_API_SECRET=your_cloudinary_api_secret
EMAIL_USER=your_gmail_address@gmail.com
EMAIL_PASS=your_gmail_app_password
```

### Backend Setup
```bash
cd backend
npm install
npx prisma migrate dev --name init
npm start
```

### Frontend Setup
```bash
cd frontend
npm install
npm start
```

## Usage
- Register a new user (email required)
- Login to access protected features (upload meal, etc.)
- Upload a meal with image and details
- Browse meals, search, and paginate
- Logout from the navbar

## Folder Structure
```
backend/
  src/
    controllers/
    middlewares/
    routes/
    utils/
frontend/
  src/
    components/
    store/
```

## Notes
- Use a Gmail App Password for `EMAIL_PASS` (not your main Gmail password)
- Cloudinary is used for image storage
- JWT tokens are stored in Redux state (not persisted after refresh unless you add persistence)

## License
MIT
