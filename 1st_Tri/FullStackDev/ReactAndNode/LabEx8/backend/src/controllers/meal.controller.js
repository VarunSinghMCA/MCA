import prisma from '../prismaClient.js';
// --------------------------------------------------------------------------------------------
// GET all meals
export const getMeals = async (req, res) => {
  try {
    const meals = await prisma.meal.findMany();
    res.json(meals);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch meals' });
  }
};

// GET single meal
export const getMeal = async (req, res) => {
  try {
    const { id } = req.params;
    const meal = await prisma.meal.findUnique({ where: { id: parseInt(id) } });
    if (!meal) return res.status(404).json({ error: 'Meal not found' });
    res.json(meal);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch meal' });
  }
};

// CREATE meal
export const createMeal = async (req, res) => {
  try {
    const { title, image, category, description } = req.body;
    const meal = await prisma.meal.create({
      data: { title, image, category, description }
    });
    res.status(201).json(meal);
  } catch (error) {
    res.status(500).json({ error: 'Failed to create meal' });
  }
};

// BULK create meal
export const bulkCreateMeals = async (req, res) => {
  try {
    const meals = req.body; // Expecting an array of meal objects
    const createdMeals = await prisma.meal.createMany({
      data: meals,
      skipDuplicates: true // Skip duplicates if any
    });
    res.status(201).json(createdMeals);
  } catch (error) {
    res.status(500).json({ error: 'Failed to create meals' });
  }
};

// UPDATE meal
export const updateMeal = async (req, res) => {
  try {
    const { id } = req.params;
    const { title, image, category, description } = req.body;
    const meal = await prisma.meal.update({
      where: { id: parseInt(id) },
      data: { title, image, category, description }
    });
    res.json(meal);
  } catch (error) {
    res.status(500).json({ error: 'Failed to update meal' });
  }
};

// DELETE meal
export const deleteMeal = async (req, res) => {
  try {
    const { id } = req.params;
    await prisma.meal.delete({ where: { id: parseInt(id) } });
    res.json({ message: 'Meal deleted' });
  } catch (error) {
    res.status(500).json({ error: 'Failed to delete meal' });
  }
};
