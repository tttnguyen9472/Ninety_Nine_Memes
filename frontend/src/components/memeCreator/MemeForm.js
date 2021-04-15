import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';
import Axios from 'axios';
import './MemeForm.css';
import {useDispatch, useSelector} from "react-redux";

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
          let requestBody = { caption: captionText, imageUrl: imgURL }
          console.log('Válasz: ', requestBody.caption, requestBody.imageUrl)


          // Axios.post('/login???', requestBody)
          //   .then(response => {
          //     setError(response);
          //   })
          //   .catch(err => {
          //     setError(err);
          //   });



          //Then ends here
        })
        .catch(err => setError(err))



    } else if (event.target.caption.value && [...formData.entries()].length === 0) {
      //küldje el a kiválasztott képet és a captiont
      captionText = event.target.caption.value;
      imgURL = activePhoto;
      let requestBody = { caption: captionText, imageUrl: imgURL }

      Axios.post('localhost URL!!!!!', requestBody)
           .then(response => {
              console.log(response);
            })
            .catch(err => {
              console.log(err);
            });
    } else {
      setError('Please add a caption and image')
    }
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

        <fieldset className="memeform-container">
          <form onSubmit={handleSubmit} className="memeFormFrom">
            <div className="img-and-button">
              <img height="300px" width="auto" src={activePhoto}></img>
            <input type="radio" name="rg" id="sign-in" checked />
            <input className="sign-up sign-in reset" name='caption' id='caption' type="text" placeholder="Write your caption here!" />
              <div>
              <button type="submit"></button>
              </div>
              {error !== '' && <p>{error}</p>}
            </div>
          </form>
        </fieldset>
      </div>
    </div>
  );
};
export default MemeForm;
