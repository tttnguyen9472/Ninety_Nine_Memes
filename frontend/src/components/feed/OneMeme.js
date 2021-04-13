import React, { useEffect, useState } from 'react';
import Reaction from './Reaction';

function OneMeme(meme) {
  console.log(meme.metadata);
  return (
    <div id={meme.id}>
      <img src={meme.img}></img>
      <label onClick={Reaction(meme, 'funny')}>
        Funny: {meme.metadata.funny}
      </label>
      <label onClick={Reaction(meme, 'cringe')}>
        Cringe: {meme.metadata.cringe}
      </label>
      <label onClick={Reaction(meme, 'horny')}>
        Horny: {meme.metadata.horny}
      </label>

      {meme.comments.map(comment => (
        <p>{comment}</p>
      ))}
    </div>
  );
}

export default OneMeme;
