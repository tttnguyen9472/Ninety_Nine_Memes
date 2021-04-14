import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Fetch from '../fetch/Fetch';
import Axios from 'axios';
import './MemeForm.css';

const MemeForm = () => {
  let activePhoto = useSelector(state => state.memeCreatorReducer.activePhoto);
  const [error, setError] = useState('');
  const formData = new FormData();

  let imgURL = '';
  let captionText = 'initial';

  function uploadImage(file) {
    formData.append('file', file);
    formData.append('upload_preset', 's13e6nbq');
  }

  function handleSubmit(event) {
    event.preventDefault();

    if (event.target.caption.value && [...formData.entries()].length > 0) {
      captionText = event.target.caption.value;

      Axios.post('https://api.cloudinary.com/v1_1/mertinsmiths/image/upload', formData)
        .then((response) => {
          imgURL = response.data.url;
        })
        .then(() => {
          //Elkuldjuk a kérést a backendre
          let requestBody = { caption: captionText, imageUrl: imgURL }
          let endpoint = `${process.env.REACT_APP_PORT}/meme`;
          console.log(endpoint)

          Axios.post(endpoint, requestBody)
            .then(response => {
              setError('Message sent');
            })
            .catch(err => {
              console.log(err);
            });


          //Elkuldjuk a kérést a backendref
          //Then ends here
        })
        .catch(err => setError(err))



    } else {
      setError('Please add a caption and image')
    }

    //Handle submit ends here
  }

  useEffect(() => {
    console.log(activePhoto);
  }, [activePhoto]);


  return (
    <div className="meme-form-container">
      <div>

        <div className='uploadField'>
          <input language='eng' type="file" name="file" onChange={(event) => {
            uploadImage(event.target.files[0])
          }} />
        </div>

        <fieldset>
          <form onSubmit={handleSubmit} className="memeFormFrom">
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
