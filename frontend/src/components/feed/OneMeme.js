import React, { useEffect, useState } from 'react';
import Reaction from './Reaction';
import { useDispatch, useSelector } from 'react-redux';

function OneMeme(meme) {
  const dispatch = useDispatch();
  return (
    <div id={meme.id}>
      <label>{meme.caption}</label>
      <br></br>
      <img src={meme.img}></img>
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

      {meme.comments.map(comment => (
        <p>{comment}</p>
      ))}
      <br></br>
    </div>
  );
}

export default OneMeme;
