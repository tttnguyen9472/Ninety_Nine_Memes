import React, { useEffect, useState } from 'react';
import Reaction from './Reaction';
import Comments from './Comments';
import { useDispatch, useSelector } from 'react-redux';

function OneMeme(meme) {
  const [commentsClicked, SetCommentsClicked] = useState(false);
  const dispatch = useDispatch();

  return (
    <div id={meme.id}>
      <label>{meme.caption}</label>
      <br></br>
      <img src={meme.img} alt={meme.caption}></img>
      <br></br>
      {meme.metadata.map(element => (
        <button onClick={() => Reaction(meme, element.type, dispatch)}>
          {element.type}: {element.value}
        </button>
      ))}
      <br></br>
      <label onClick={() => SetCommentsClicked(!commentsClicked)}>
        {(commentsClicked && 'Hide Comments') || 'Show Comments'}
      </label>
      {commentsClicked && Comments(meme, dispatch)}
      <br></br>
    </div>
  );
}

export default OneMeme;
