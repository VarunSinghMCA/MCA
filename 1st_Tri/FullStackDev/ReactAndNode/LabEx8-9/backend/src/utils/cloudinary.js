import { v2 as cloudinary } from 'cloudinary';
import fs from 'fs'
import dotenv from 'dotenv';

// Load environment variables from .env file
dotenv.config({
    path: "./.env"
});

//console.log("\n\nENV >>>>> \n",process.env.CLOUDINARY_CLOUD_NAME,"\n",process.env.CLOUDINARY_API_KEY,"\n",process.env.CLOUDINARY_API_SECRET,"\n")

// Cloudinary Configuration
cloudinary.config({ 
    cloud_name: process.env.CLOUDINARY_CLOUD_NAME, 
    api_key: process.env.CLOUDINARY_API_KEY,
    api_secret: process.env.CLOUDINARY_API_SECRET
});

const uploadOnCloudinay = async (localFilePath) => {
    try{
        // if(!localFilePath) return null; 
        if (!localFilePath) throw new Error('\nNo file path provided\n');
        // upload file to cloudinary
        const uploadResult = await cloudinary.uploader.upload( localFilePath, {
            resource_type: "auto",
        });
        // file uploaded - url is one of the parameters when file is uploaded has
        // console.log("File successfully uploaded, uploadResult >>>>>>>>",uploadResult);

        return uploadResult;

    }catch(error){

        console.error("\n\nError uploading file to Cloudinary >>>>>>>\n", error);

        fs.unlinkSync(localFilePath); // remover local file if upload fails
        
        // return null;
    }
}

export { uploadOnCloudinay }
    