import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Fetch from '../fetch/Fetch';
import './MemeForm.css';

const MemeForm = () => {
  let activePhoto = useSelector(state => state.memeCreatorReducer.activePhoto);
  useEffect(() => {
    console.log(activePhoto);
  }, [activePhoto]);
  return (
    <div className="meme-form-container">
      <div>
      <fieldset>
        <form>
            <input type="radio" name="rg" id="sign-in" checked/>
            <input className="sign-up sign-in reset" name='username' id='username' type="text" placeholder="Write your caption here!" />
            <img src={activePhoto}></img> 
            <button type="submit"></button>       
        </form>
    </fieldset>
      </div>    
    </div>
  );
};
export default MemeForm;
