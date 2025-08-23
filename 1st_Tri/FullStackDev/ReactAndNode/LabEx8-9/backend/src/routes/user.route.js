import express from 'express';

const userRouter = express.Router();

import { login, signup, updateUser, deleteUser, getUserContents } from '../controllers/user.controller.js';


import { authenticate } from '../middlewares/auth.middleware.js';

userRouter.post("/signup", signup);
userRouter.post("/login", login);

// Protected routes
userRouter.get("/contents", authenticate, getUserContents);
userRouter.put("/update", authenticate, updateUser);
userRouter.delete("/delete", authenticate, deleteUser);

export default userRouter;
