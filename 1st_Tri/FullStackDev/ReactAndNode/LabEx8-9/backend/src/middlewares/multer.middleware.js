import multer from "multer"

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, "./public/temp")
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9)
    cb(null, file.originalname + '-' + uniqueSuffix)  // for adding names to the files added to disk storage
  }
})
  
export const upload = multer({ storage: storage }); // In es6 if both names are same i.e. here, (storage: storage) you can just write it as storage