import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';
import { useDispatch, useSelector } from 'react-redux';
import OneMeme from './OneMeme';

const Feed = () => {
  const dispatch = useDispatch();
  const memeList = useSelector(state => state.memeReducer.memeList);
  const errormessage = useSelector(state => state.memeReducer.FeedErrormessage);
  const test = [
    {
      id: 1,
      timestamp: 1618319065,
      metadata: { funny: 5, cringe: 0, horny: 1 },
      comments: ['WTF?', 'YEY'],
      img: 'https://cdn.kapwing.com/video_image-7PxmyJIHJ.jpg',
    },
    {
      id: 2,
      timestamp: 1618318065,
      metadata: { funny: 3, cringe: 4, horny: 2 },
      comments: ['?????', 'PISTI KÉSZ AZ EBÉD'],
      img: 'https://i.imgflip.com/3w0vvy.png',
    },
  ];

  useEffect(() => {
    Fetch('GET', '/meme')
      .then(response => {})
      .catch(error => dispatch({ type: 'FEED_ERROR', error: error }));
  }, [memeList]);

  if (errormessage) {
    return <div>{errormessage}</div>;
  } else {
    return <div>{memeList.map(meme => OneMeme(meme))}</div>;
  }
};

export default Feed;
