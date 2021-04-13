import { combineReducers } from 'redux';
import userReducer from '../reducers/userReducer';
import memeReducer from '../reducers/memeReducer';

const rootReducer = combineReducers({
  'userReducer': userReducer,
  'memeReducer': memeReducer
})

export default rootReducer