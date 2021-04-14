import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';

function Reaction(meme, type, dispatch) {
  console.log(meme.id + ': ' + type);
  dispatch({ type: 'FEED_STATE_CHANGE' });
  /*Fetch('POST', '/meme/reaction', {
    memeId: meme.id,
    type: type,
  })
    .then(response => dispatch({ type: 'FEED_STATE_CHANGE' }))
    .catch(err => dispatch({ type: 'FEED_ERROR', error: err }));*/
}

export default Reaction;
