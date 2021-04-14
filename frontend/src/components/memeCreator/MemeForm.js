import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Fetch from '../fetch/Fetch';
import './MemeForm.css';

const MemeForm = () => {
  let activePhoto = useSelector(state => state.memeCreatorReducer.activePhoto);
  const [error, setError] = useState('');

  function handleSubmit(event) {
    event.preventDefault();

    //Feltöltjük a képünket egy API-ra
    //Az API ad egy URL-t
    //Beírjuk ezt az URL-t a imgData-ban
    //Amikor megjenítünk valahol valamit, akkor ezt az eltárolt url-t kell beírni a src-be

    let body = {
      caption: event.target.caption.value || 'No caption',
      imgData: 'https//memestorage.api.com/asdsadas'
    }

    console.log(body)
    Fetch('POST', '/login???', body)
      .then(response => {
        setError(response);
      })
      .catch(err => {
        setError(err);
      });
  }

  useEffect(() => {
    console.log(activePhoto);
  }, [activePhoto]);


  return (
    <div className="meme-form-container">
      
      <div>
        <fieldset>
          <form onSubmit={handleSubmit}>
            <input type="radio" name="rg" id="sign-in" checked />
            <input className="sign-up sign-in reset" name='caption' id='caption' type="text" placeholder="Write your caption here!" />
            <div className="img-and-button">
              <img src={activePhoto}></img>
              <button type="submit"></button>
              {error !== '' && <p>{error}</p>}
            </div>
          </form>
        </fieldset>
      </div>
    </div>
  );
};
export default MemeForm;
