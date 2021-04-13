import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';
import { useDispatch, useSelector } from 'react-redux';

function Reaction(meme, type) {
  const dispatch = useDispatch();

  /*Fetch('POST', '/meme/reaction', {
    memeId: meme.id,
    type: type,
  })
    .then(response => dispatch({ type: 'FEED_STATE_CHANGE' }))
    .catch(err => dispatch({ type: 'FEED_ERROR', error: error }));*/
}

export default Reaction;
