import React, { useEffect, useState } from 'react';
import Reaction from './Reaction';
import Fetch from '../fetch/Fetch';
import Comments from './Comments';
import Popup from './Popup';
import './Dropdown.css';
import './OneMeme.css';
import cringe from '../../assets/cringe.png';
import funny from '../../assets/funny.png';
import horny from '../../assets/horny.png';
import { useDispatch, useSelector } from 'react-redux';

function OneMeme(meme) {
  const [commentsClicked, SetCommentsClicked] = useState(false);
  const dispatch = useDispatch();
  const [isOpen, setIsOpen] = useState(false);
  const [value, setValue] = useState(null);
  const [type, setType] = useState(null);
  const options = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  function togglePopup(typeIn, valueIn) {
    setValue(valueIn);
    setType(typeIn);
    setIsOpen(!isOpen);
  }

  function Rate(typeIn, valueIn, memeId) {
    console.log(typeIn, valueIn, memeId);
    /*Fetch('POST', '/meme/reaction', {
      memeId: memeId,
      type: typeIn,
      value: valueIn
    })
      .then(response => dispatch({ type: 'FEED_STATE_CHANGE' }))
      .catch(err => dispatch({ type: 'FEED_ERROR', error: err }));*/
    setIsOpen(!isOpen);
  }

  function getImage(typeIn) {
    if (typeIn === 'funny') {
      return funny;
    } else if (typeIn === 'cringe') {
      return cringe;
    } else if (typeIn === 'horny') {
      return horny;
    }
  }

  return (
    <div className="one-meme">
      <label className="meme-label">{meme.caption}</label>
      <br></br>
      <img src={meme.img} alt={meme.caption}></img>
      <br></br>
      {meme.metadata.map(element => (
        <button
          className="onememe-button"
          onClick={() => togglePopup(element.type, element.value)}
        >
          <img className="reaction-img" src={getImage(element.type)}></img>{' '}
          {element.value}
        </button>
      ))}
      {isOpen && (
        <Popup
          content={
            <>
              <div className="dropdown">
                <button className="dropbtn">
                  Rate how much {type} this meme is:
                </button>
                <div className="dropdown-content">
                  {options.map(option => (
                    <a onClick={() => Rate(type, option, meme.id)}>{option}</a>
                  ))}
                </div>
              </div>
            </>
          }
          handleClose={() => togglePopup('', '')}
        />
      )}
      <br></br>

      <label
        className="comment-visibality"
        onClick={() => SetCommentsClicked(!commentsClicked)}
      >
        {(commentsClicked && 'Hide Comments') || 'Show Comments'}
      </label>
      {commentsClicked && Comments(meme, dispatch)}
      <br></br>
    </div>
  );
}

export default OneMeme;
