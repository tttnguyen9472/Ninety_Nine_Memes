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

  function uploadImage(file) {
    formData.append('file', file);
    formData.append('upload_preset', 's13e6nbq');
  }


  function handleSubmit(event) {
    event.preventDefault();
    let caption = event.target.caption.value || 'No caption';

    Axios.post('https://api.cloudinary.com/v1_1/mertinsmiths/image/upload', formData)
      .then((response) => {
        console.log('URL to set: '+ response.data.url)
        imgURL = response.data.url;
      })
      .then(()=> {
        let requestBody = {caption: caption, imageUrl: imgURL}

        
        // Axios.post('/login???', requestBody)
        //   .then(response => {
        //     setError(response);
        //   })
        //   .catch(err => {
        //     setError(err);
        //   });



        //Then ends here
      })
      .catch(err => console.log(err))

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
