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
      <label onClick={() => Reaction(meme, 'funny', dispatch)}>
        Funny: {meme.metadata.funny}
      </label>
      <label onClick={() => Reaction(meme, 'cringe', dispatch)}>
        Cringe: {meme.metadata.cringe}
      </label>
      <label onClick={() => Reaction(meme, 'horny', dispatch)}>
        Horny: {meme.metadata.horny}
      </label>
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
