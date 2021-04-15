import './MemeCreator.css';
import React, { useEffect, useState } from 'react';
import MemeForm from './MemeForm';
import Gallery from './Gallery';

const MemeCreator = () => {
  return (
    <div className="meme-creator-container">
      <MemeForm />
      <Gallery />
    </div>
  );
};
export default MemeCreator;
