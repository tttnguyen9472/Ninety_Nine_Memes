const initialState = {
  errormessageRegister: '',
  errormessageLogin: '',
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'REGISTER_MISSING_PASSWORD_AND_USERNAME':
      return {
        ...state,
        errormessageRegister: 'Missing password and username!',
      };
    case 'REGISTER_MISSING_PASSWORD':
      return {
        ...state,
        errormessageRegister: 'Missing password!',
      };
    case 'REGISTER_MISSING_USERNAME':
      return {
        ...state,
        errormessageRegister: 'Missing username!',
      };
    case 'PASSWORD_UNDER_8_CHARACTERS':
      return {
        ...state,
        errormessageRegister: 'Password must be at least 8 characters!',
      };
    case 'REGISTER_ERROR':
      return {
        ...state,
        errormessageRegister: action.errormessage,
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
        errormessageLogin: '',
        errormessageRegister: '',
      };
    default:
      return state;
  }
};

export default userReducer;

