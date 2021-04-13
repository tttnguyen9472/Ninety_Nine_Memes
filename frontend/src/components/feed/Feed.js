import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';
import { useDispatch, useSelector } from 'react-redux';
import OneMeme from './OneMeme';

const Feed = () => {
  const dispatch = useDispatch();

  const memeList = useSelector(state => state.memeReducer.memeList);
  const errormessage = useSelector(state => state.memeReducer.FeedErrormessage);

  useEffect(() => {
    /*Fetch('GET', '/meme')
      .then(response => {})
      .catch(error => dispatch({ type: 'FEED_ERROR', error: error }));*/
    dispatch({ type: 'FILL_MEME_LIST', memeList: memeList });
  }, [memeList]);

  if (errormessage) {
    return <div>{errormessage}</div>;
  } else {
    return <div>{memeList.map(meme => OneMeme(meme))}</div>;
  }
};

export default Feed;
