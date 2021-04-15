import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Fetch from '../fetch/Fetch';
import OneMeme from './OneMeme';
import Popup from './Popup';

const Feed = () => {
  const dispatch = useDispatch();

  const memeList = useSelector(state => state.memeReducer.memeList);
  const errormessage = useSelector(state => state.memeReducer.FeedErrormessage);
  const feedState = useSelector(state => state.memeReducer.FeedState);
  const [isOpen, setIsOpen] = useState(false);
  const togglePopup = () => {
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    /*Fetch('GET', '/meme')
      .then(response => {})
      .catch(error => dispatch({ type: 'FEED_ERROR', error: error }));*/
    dispatch({ type: 'FILL_MEME_LIST', memeList: memeList });
  }, [feedState]);

  if (errormessage) {
    return <div>{errormessage}</div>;
  } else {
    return <div>{memeList.map(meme => OneMeme(meme))}</div>;
  }
};

export default Feed;
