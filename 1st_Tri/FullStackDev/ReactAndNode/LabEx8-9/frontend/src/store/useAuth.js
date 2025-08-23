import { useSelector, useDispatch } from 'react-redux';

export const useAuth = () => {
  const token = useSelector((state) => state.auth.token);
  const username = useSelector((state) => state.auth.username);
  return { token, username };
};
