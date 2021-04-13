const initialState = {
  activePhoto: '',
};

const memeCreatorReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SELECT_ACTIVE_PHOTO':
      return {
        ...state,
        activePhoto: action.activePhoto,
      };
    case 'CLEAR_FIELDS':
      return {
        ...state,
        activePhoto: '',
      };
    default:
      return state;
  }
};

export default memeCreatorReducer;
