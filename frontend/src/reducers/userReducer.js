const initialState = {
  errormessage: '',
  username: '',
  password: ''
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'CHANGE_USERNAME':
      return {
        ...state,
        username: action.username,
      };
      case 'CHANGE_PASSWORD':
      return {
        ...state,
        password: action.password,
      };
    case 'MISSING_PASSWORD_AND_USERNAME':
      return {
        ...state,
        errormessage: 'Missing password and username!',
      };
    case 'MISSING_PASSWORD':
      return {
        ...state,
        errormessage: 'Missing password!',
      };
    case 'MISSING_USERNAME':
      return {
        ...state,
        errormessage: 'Missing username!',
      };
    case 'PASSWORD_UNDER_8_CHARACTERS':
      return {
        ...state,
        errormessage: 'Password must be at least 8 characters!',
      };
    case 'BACKEND_ERROR':
      return {
        ...state,
        errormessage: action.errormessage,
      };

    case 'LOGIN_MISSING_USERNAME_OR_PASSWORD':
      return {
        ...state,
        errormessageLogin: 'Missing username or password!',
      };
    case 'LOGIN_BACKEND_ERROR':
      return {
        ...state,
        errormessageLogin: action.errormessage,
      };
    case 'CLEAR_FIELDS':
      return {
        ...state,
        errormessage: '',
        username: '',
        password: ''
      };
    default:
      return state;
  }
};

export default userReducer;

