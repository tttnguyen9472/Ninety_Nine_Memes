const initialState = {
  memeList: [
    {
      id: 1,
      caption: 'Nem tudom',
      timestamp: 1618319065,
      metadata: [
        { type: 'funny', value: 5 },
        { type: 'cringe', value: 2 },
        { type: 'horny', value: 4 },
      ],
      comments: ['WTF?', 'YEY'],
      img: 'https://cdn.kapwing.com/video_image-7PxmyJIHJ.jpg',
    },
    {
      id: 2,
      timestamp: 1618318065,
      caption: 'Még most se tudom',
      metadata: [
        { type: 'funny', value: 3 },
        { type: 'cringe', value: 8 },
        { type: 'horny', value: 6 },
      ],
      comments: ['?????', 'PISTI KÉSZ AZ EBÉD'],
      img: 'https://i.imgflip.com/3w0vvy.png',
    },
  ],
  FeedErrormessage: null,
  FeedState: false,
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
    case 'FEED_STATE_CHANGE':
      return {
        ...state,
        FeedState: !state.FeedState,
      };
    default:
      return state;
  }
};

export default memeReducer;
