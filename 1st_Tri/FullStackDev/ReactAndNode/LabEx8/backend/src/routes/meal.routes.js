import express from 'express';
import {
  getMeals,
  getMeal,
  createMeal,
  updateMeal,
  deleteMeal,
  bulkCreateMeals
} from '../controllers/meal.controller.js';

const mealRouter = express.Router();

mealRouter.get('/', getMeals);
mealRouter.get('/:id', getMeal);
mealRouter.post('/', createMeal);
mealRouter.post('/bulk', bulkCreateMeals);
mealRouter.put('/:id', updateMeal);
mealRouter.delete('/:id', deleteMeal);

export default mealRouter;  
