import prisma from "../prismaClient.js";
import { uploadOnCloudinay } from "../utils/cloudinary.js";
import { v2 as cloudinary } from "cloudinary";
import fs from "fs";
import { asyncHandler } from "../utils/asyncHandler.js";
import { ApiError } from "../utils/ApiError.js";
import { ApiResponse } from "../utils/ApiResponse.js";

// GET all meals
export const getMeals = asyncHandler(async (req, res) => {
  const meals = await prisma.meal.findMany();
  return res
    .status(200)
    .json(new ApiResponse(200, "Meals fetched successfully", meals));
});

// GET single meal
export const getMeal = asyncHandler(async (req, res) => {
  const { id } = req.params;
  const meal = await prisma.meal.findUnique({ where: { id: parseInt(id) } });
  if (!meal) throw new ApiError(404, "Meal not found");
  return res
    .status(200)
    .json(new ApiResponse(200, "Meal fetched successfully", meal));
});

// CREATE meal with image
export const createMeal = asyncHandler(async (req, res) => {
  const { title, category, description } = req.body;
  let imageUrl = null;
  let imageId = null;

  if (req.file) {
    const uploadResult = await uploadOnCloudinay(req.file.path);
    if (uploadResult) {
      imageUrl = uploadResult.secure_url;
      imageId = uploadResult.public_id;
      if (fs.existsSync(req.file.path)) fs.unlinkSync(req.file.path);
    }
  }

  const meal = await prisma.meal.create({
    data: { title, image: imageUrl, imageId, category, description },
  });

  return res
    .status(201)
    .json(new ApiResponse(201, "Meal created successfully", meal));
});

// BULK create meals
export const bulkCreateMeals = asyncHandler(async (req, res) => {
  const meals = req.body; // array data
  if (!Array.isArray(meals) || meals.length === 0) {
    throw new ApiError(400, "Request body must be an array of meals");
  }

  const createdMeals = await prisma.meal.createMany({
    data: meals,
    skipDuplicates: true,
  });

  return res
    .status(201)
    .json(new ApiResponse(201, "Meals created successfully", createdMeals));
});

// UPDATE meal (with optional new image)
export const updateMeal = asyncHandler(async (req, res) => {
  const { id } = req.params;
  const { title, category, description } = req.body;

  const existingMeal = await prisma.meal.findUnique({
    where: { id: parseInt(id) },
  });

  if (!existingMeal) throw new ApiError(404, "Meal not found");

  let imageUrl = existingMeal?.image;
  let imageId = existingMeal?.imageId;

  if (req.file) {
    if (existingMeal.imageId) {
      await cloudinary.uploader.destroy(existingMeal.imageId);
    }

    const uploadResult = await uploadOnCloudinay(req.file.path);
    if (uploadResult) {
      imageUrl = uploadResult.secure_url;
      imageId = uploadResult.public_id;
      if (fs.existsSync(req.file.path)) fs.unlinkSync(req.file.path);
    }
  }

  const meal = await prisma.meal.update({
    where: { id: parseInt(id) },
    data: { title, category, description, image: imageUrl, imageId },
  });

  return res
    .status(200)
    .json(new ApiResponse(200, "Meal updated successfully", meal));
});

// DELETE meal
export const deleteMeal = asyncHandler(async (req, res) => {
  const { id } = req.params;
  const meal = await prisma.meal.findUnique({ where: { id: parseInt(id) } });

  if (!meal) throw new ApiError(404, "Meal not found");

  if (meal.imageId) {
    await cloudinary.uploader.destroy(meal.imageId);
  }

  await prisma.meal.delete({ where: { id: parseInt(id) } });

  return res
    .status(200)
    .json(new ApiResponse(200, "Meal deleted successfully", null));
});
