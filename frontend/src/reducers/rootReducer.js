import { combineReducers } from 'redux';
import userReducer from '../reducers/userReducer';
import memeCreatorReducer from './memeCreatorReducer';

const rootReducer = combineReducers({
  userReducer: userReducer,
  memeCreatorReducer: memeCreatorReducer,
});

export default rootReducer;
