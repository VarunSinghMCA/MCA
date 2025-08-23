import prisma from "../prismaClient.js";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";
import { asyncHandler } from "../utils/asyncHandler.js";
import { ApiError } from "../utils/ApiError.js";
import { ApiResponse } from "../utils/ApiResponse.js";
import { sendRegistrationEmail } from "../utils/mailer.js";


// Secret key for JWT
const JWT_SECRET = process.env.JWT_SECRET ;
if (!JWT_SECRET) throw new Error("JWT_SECRET is not defined");

// ------------------------- User signup ----------------------------
export const signup = asyncHandler(async (req, res) => {
  const { email, password, username } = req.body;
  const existingUser = await prisma.user.findUnique({ where: { email } });
  if (existingUser) {
    throw new ApiError(400, "User already exists");
  }
  const hashedPassword = await bcrypt.hash(password, 10);
  const user = await prisma.user.create({
    data: {
      email,
      password: hashedPassword,
      username,
    },
  });

  // Send registration email (do not block response on error)
  sendRegistrationEmail(email, username).catch((err) => {
    console.error('Failed to send registration email:', err);
  });

  return res.status(201).json(new ApiResponse(201, "User created successfully", user));
});

// ----------------------- User login ------------------------------
export const login = asyncHandler(async (req, res) => {
  const { email, password } = req.body;
  const user = await prisma.user.findUnique({ where: { email } });
  if (!user) {
    throw new ApiError(400, "Invalid email or password");
  }
  const isPasswordValid = user.password && (await bcrypt.compare(password, user.password));
  if (!isPasswordValid) {
    throw new ApiError(400, "Invalid email or password");
  }
  const token = jwt.sign({ userId: user.id }, JWT_SECRET, { expiresIn: "1h" });
  return res.status(200).json(new ApiResponse(200, "Login successful", { token, userId: user.id, username: user.username }));
});



// Get user contents (protected)
export const getUserContents = asyncHandler(async (req, res) => {
  const userId = req.user?.id;
  if (!userId) {
    throw new ApiError(401, "Unauthorized: User not authenticated");
  }
  const contents = await prisma.content.findMany({
    where: { authorId: userId },
  });
  return res.status(200).json(new ApiResponse(200, "User contents fetched", contents));
});



// Update user (protected)
export const updateUser = asyncHandler(async (req, res) => {
  const userId = req.user?.id;
  const { username, email, password } = req.body;
  if (!userId) {
    throw new ApiError(401, "Unauthorized: User not authenticated");
  }
  const data = {};
  if (username) data.username = username;
  if (email) data.email = email;
  if (password) data.password = await bcrypt.hash(password, 10);
  const updatedUser = await prisma.user.update({
    where: { id: userId },
    data,
  });
  return res.status(200).json(new ApiResponse(200, "User updated successfully", updatedUser));
});



// Delete user (protected)
export const deleteUser = asyncHandler(async (req, res) => {
  const userId = req.user?.id;
  if (!userId) {
    throw new ApiError(401, "Unauthorized: User not authenticated");
  }
  await prisma.user.delete({ where: { id: userId } });
  return res.status(200).json(new ApiResponse(200, "User deleted successfully", null));
});