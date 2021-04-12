const initialState = {
  errormessageRegister: '',
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
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
