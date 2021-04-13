import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Fetch from '../fetch/Fetch';
import './MemeForm.css';

const MemeForm = () => {
  let activePhoto = useSelector(state => state.memeCreatorReducer.activePhoto);
  const [error, setError] = useState('');

  function handleSubmit(event) {
    event.preventDefault();

    //Feltöltünk egy képet és ennek a linkjét küldjük el

    let body = {
      caption: 'captionvalue',
      imgData: 'imgur.com/asdsadas'
    }

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
            <input className="sign-up sign-in reset" name='username' id='username' type="text" placeholder="Write your caption here!" />
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
