import React, { useEffect, useState } from 'react';
import './Form.css';
import Fetch from '../fetch/Fetch';
import { useHistory } from 'react-router-dom';
import InputField from "./InputField";
import {useDispatch, useSelector} from "react-redux";

const Form = () => {

  let history = useHistory();
  const dispatch = useDispatch();
  const errormessage = useSelector(state => state.userReducer.errormessageLogin);
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [submitted, setSubmitted] = useState(false);

  function handleUsernameChange(event) {
    let container = event.target.value;
    setUserName(() => container);
  }

  function handlePasswordChange(event) {
    let container = event.target.value;
    setPassword(() => container);
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (password === '' || userName === '') {
      console.log('MESSAGE SENT')
      return dispatch({type: 'LOGIN_MISSING_USERNAME_OR_PASSWORD'});
    } else {
      setSubmitted(true);
    }
  }

  useEffect(() => {
    if (submitted) {
      let body = { username: userName, password: password };
      //IDE KELL EGY NORMÁLIS ENDPOINT
      Fetch('POST', '/login??????', body)
        .then(response => {
          localStorage.setItem('token', response.token);
          //FŐ OLDAL ÁTIRÁNYÍTÁSA
          history.push('/');
          return dispatch({type: 'CLEAR_FIELDS'});
        })
        .catch(err => {
          return dispatch({type: 'LOGIN_BACKEND_ERROR', errormessage: err.toString()});
        });
      setSubmitted(false);
    }
  }, [submitted]);

  return (
    <div className="login-form-container">
      <form autoComplete="off" onSubmit={handleSubmit}>
        <InputField
          onChange={handleUsernameChange}
          placeholder="Username"
          name="username"
          id="username"
          type="text"
        />
        <br />
        <InputField
          onChange={handlePasswordChange}
          placeholder="Password"
          name="password"
          id="password"
          type="password"
        />
        <br />
        {errormessage && <span className="login-errormessage">{errormessage}</span>}
        <button type="submit">LOGIN</button>
      </form>
    </div>
  );
};

export default Form;
