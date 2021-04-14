import { Image, Video, Transformation, CloudinaryContext } from 'cloudinary-react';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Axios from 'axios';
import './UploadFile.css';



function UploadFile() {

  function uploadImage(file){
    console.log('File: '+ file);

    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', 's13e6nbq');

    Axios.post('https://api.cloudinary.com/v1_1/mertinsmiths/image/upload', formData)
    .then((response) => {console.log(response.data.url)})
    .catch(err => console.log(err))
  }


  return (
    <div className='uploadField'>

      <input language='eng' type="file" name="file" onChange={(event) => {
        uploadImage(event.target.files[0])
      }}/>

    </div>
  )
}


export default UploadFile