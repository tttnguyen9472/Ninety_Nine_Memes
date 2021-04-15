import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';
import './Gallery.css';
import {useDispatch, useSelector} from "react-redux";
import bernie from '../../assets/bernie.jpg';
import chad from '../../assets/chad.jpg';
import computerguy from '../../assets/computerguy.jpg';
import cosmic from '../../assets/cosmic.jpg';
import headpain from '../../assets/headpain.jpg';
import meme from '../../assets/meme.jpg';
import axios from 'axios';
var ScrollArea = require('react-scrollbar');

const Gallery = () => {
  let activePhoto = useSelector(state => state.memeCreatorReducer.activePhoto);
  const dispatch = useDispatch();
  let photosArr = [bernie, chad, computerguy, cosmic, headpain, meme];
  function getPhotos (photo) {
  return (<img  className="photo" src={photo} onClick={() => { console.log(activePhoto);
    return dispatch({type: 'SELECT_ACTIVE_PHOTO', activePhoto: photo})}}></img>)
}
  const [templates, setTemplates] = useState([]);
  useEffect(() => {
    axios.get('https://api.imgflip.com/get_memes')
  .then((res) => {setTemplates(res.data.data.memes)});
  },[])

  return (
    <div className="gallery-container">
     <div className="gallery-selectable-photos-wrapper">
     <h3 className="gallery-title">Choose a photo!</h3>
       <div className="scrollbar">
       {templates.map(template => {
         return <img id={template.id} src={template.url} width="200px" height="auto" onClick={() => {return dispatch({type: 'SELECT_ACTIVE_PHOTO', activePhoto: template.url})}}></img>
       })}
       </div>
     </div>
    </div>
  )

}
export default Gallery;