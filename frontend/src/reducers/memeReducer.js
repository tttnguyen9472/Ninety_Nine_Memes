const initialState = {
  memeList: [],
  FeedErrormessage: null,
};

const memeReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'FILL_MEME_LIST':
      return {
        ...state,
        memeList: action.memeList,
      };
    case 'FEED_ERROR':
      return {
        ...state,
        FeedErrormessage: action.error,
      };
    case 'CLEAR_FEED_ERROR':
      return {
        ...state,
        FeedErrormessage: null,
      };
    default:
      return state;
  }
};

export default memeReducer;
