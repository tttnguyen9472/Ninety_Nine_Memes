import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';

function Reaction(meme, type) {
  if (type === 'funny') {
    meme.metadata.funny += 1;
  }
  console.log(meme.metadata);
}

export default Reaction;
