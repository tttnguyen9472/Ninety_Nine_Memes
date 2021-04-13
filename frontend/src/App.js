import React from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';
import Login from './components/login/FormStyled'
import MemeCreator from './components/memeCreator/MemeCreator';

function App() {
  return (
    <div className="App">
    <Login />
    <MemeCreator/>
    </div>
  );
}

export default App;
