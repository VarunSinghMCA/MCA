import { configureStore } from '@reduxjs/toolkit';
import rootReducer from './reducers/index.js';

const store = configureStore({
  reducer: rootReducer, // Combine your reducers here
});

export default store;