import express from 'express';
import {
  getMeals,
  getMeal,
  createMeal,
  updateMeal,
  deleteMeal,
  bulkCreateMeals
} from '../controllers/meal.controller.js';

import { upload } from '../middlewares/multer.middleware.js';

const mealRouter = express.Router();

mealRouter.get('/', getMeals);
mealRouter.get('/:id', getMeal);

// create meal with single image upload
mealRouter.post('/', upload.single("image"), createMeal);

// bulk create without images (from JSON body)
mealRouter.post('/bulk', bulkCreateMeals);

// update meal (with optional new image)
mealRouter.put('/:id', upload.single("image"), updateMeal);

// delete meal (will also delete Cloudinary image)
mealRouter.delete('/:id', deleteMeal);

export default mealRouter;
