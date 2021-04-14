import React from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';
import Login from './components/login/FormStyled'
import UploadFile from './components/memeCreator/UploadFile';
import FormStyled from './components/login/FormStyled';
import MemeForm from './components/memeCreator/MemeForm';
import MemeCreator from './components/memeCreator/MemeCreator';

function App() {
  return (
    <div className="App">
      {/* <UploadFile /> */}
      {/* <MemeForm /> */}
      {/* <MemeCreator /> */}
      <FormStyled/>
    </div>
  );
}

export default App;
