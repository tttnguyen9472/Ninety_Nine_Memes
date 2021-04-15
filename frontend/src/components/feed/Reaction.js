import React, { useEffect, useState } from 'react';
import './Popup.css';
import Fetch from '../fetch/Fetch';
import Popup from './Popup';

function Reaction(meme, type, dispatch) {
  console.log(meme.id + ': ' + type);
  return (
    <div className="popup-box">
      <div className="box">
        <span className="close-icon">x</span>
        Asd
      </div>
    </div>
  );

  /*Fetch('POST', '/meme/reaction', {
    memeId: meme.id,
    type: type,
  })
    .then(response => dispatch({ type: 'FEED_STATE_CHANGE' }))
    .catch(err => dispatch({ type: 'FEED_ERROR', error: err }));*/
}

export default Reaction;
