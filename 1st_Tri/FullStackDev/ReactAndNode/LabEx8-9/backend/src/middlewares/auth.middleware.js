import jwt from "jsonwebtoken";
import { asyncHandler } from "../utils/asyncHandler.js";
import { ApiError } from "../utils/ApiError.js";

const { verify } = jwt;
const JWT_SECRET = process.env.JWT_SECRET;
if (!JWT_SECRET) {
  throw new Error("JWT_SECRET is not defined");
}

export const authenticate = asyncHandler((req, res, next) => {
  const authHeader = req.headers.authorization;
  if (!authHeader || !authHeader.startsWith("Bearer ")) {
    throw new ApiError(401, "Unauthorized: Token missing");
  }
  const token = authHeader.split(" ")[1];
  try {
    const decoded = verify(token, JWT_SECRET);
    req.user = { id: decoded.userId };
    next();
  } catch (error) {
    throw new ApiError(401, "Unauthorized: Invalid token");
  }
});