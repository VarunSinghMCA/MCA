// import { v2 as cloudinary } from "cloudinary";
// import multer from "multer";
// import { CloudinaryStorage } from "multer-storage-cloudinary";
// import dotenv from "dotenv";

// dotenv.config();

// // Cloudinary configuration
// cloudinary.config({
//   cloud_name: process.env.CLOUDINARY_CLOUD_NAME,
//   api_key: process.env.CLOUDINARY_API_KEY,
//   api_secret: process.env.CLOUDINARY_API_SECRET,
// });

// // Multer storage setup with Cloudinary
// const storage = new CloudinaryStorage({
//   cloudinary: cloudinary,
//   params: {
//     folder: "uploads", // Folder name in Cloudinary
//     resource_type: "auto", // Allows both image & video
//     public_id: (req, file) =>
//       `${Date.now()}-${file.originalname.split(".")[0]}`, // Custom filename
//   },
// });

// const upload = multer({ storage });

// // Controller function to handle upload
// export const uploadFile = (req, res) => {
//   try {
//     if (!req.file) {
//       return res.status(400).json({ message: "No file uploaded" });
//     }

//     res.status(200).json({
//       message: "File uploaded successfully",
//       url: req.file.path, // Cloudinary file URL
//       public_id: req.file.filename, // Cloudinary public ID
//     });
//   } catch (error) {
//     console.error("Upload Error:", error);
//     res.status(500).json({ message: "Upload failed", error });
//   }
// };

// // Export upload middleware so you can use in routes
// export { upload };
