import React, { useEffect, useState } from 'react';
import './FormStyled.css';
import Fetch from '../fetch/Fetch';
import { useHistory } from 'react-router-dom';
//import InputField from "./InputField";
import {useDispatch, useSelector} from "react-redux";

const FormStyled = () => {

  let history = useHistory();
  const dispatch = useDispatch();
  const errormessage = useSelector(state => state.userReducer.errormessageLogin);
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');

   let usernameEvent = '';
   let passwordEvent = '';

  function handleUsernameChange(event) {
    usernameEvent = event.target.value;
    console.log(usernameEvent);
    setUserName(usernameEvent);
  }

  function handlePasswordChange(event) {
    passwordEvent= event.target.value;
    setPassword(passwordEvent);
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(userName, usernameEvent);
    if (password === '' || userName === '') {
      console.log(password, userName)
    } 
  }

  useEffect(() => {
      let body = { username: userName, password: password };
      //IDE KELL EGY NORMÁLIS ENDPOINT
      Fetch('POST', '/login??????', body)
        .then(response => {
          localStorage.setItem('token', response.token);
          //FŐ OLDAL ÁTIRÁNYÍTÁSA
          history.push('/login');
          return dispatch({type: 'CLEAR_FIELDS'});
        })
        .catch(err => {
          return dispatch({type: 'LOGIN_BACKEND_ERROR', errormessage: err.toString()});
        });
  }, []);

  return (
    <div className="login-form-container">
      <div className="flex-wrap">
    <fieldset>
        <form autoComplete="off" onSubmit={handleSubmit}>
            <input type="radio" name="rg" id="sign-in" checked/>
            <input type="radio" name="rg" id="sign-up" />      

            <label htmlFor="sign-in">Login</label>
            <label htmlFor="sign-up">Register</label> 

            <input className="sign-up sign-in reset" type="text" placeholder="Username" onChange={handleUsernameChange}/>
            <input className="sign-up sign-in" type="password" placeholder ="Password" />
            <input className="sign-up" type="password" placeholder ="Repeat Password" />
            
            <button type="submit"></button>       

            <p>{errormessage && <span className="login-errormessage">{errormessage}</span>}</p>
        </form>
    </fieldset>
</div>
    </div>
  );
};

export default FormStyled;
