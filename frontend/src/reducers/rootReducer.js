import { combineReducers } from 'redux';
import userReducer from '../reducers/userReducer';
import memeCreatorReducer from './memeCreatorReducer';
import memeReducer from '../reducers/memeReducer';

const rootReducer = combineReducers({
  userReducer: userReducer,
  memeCreatorReducer: memeCreatorReducer,
  memeReducer: memeReducer,
});

export default rootReducer;
