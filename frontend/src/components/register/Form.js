import React, { useState, useEffect } from 'react';
import InputField from './InputField';
import Fetch from '../fetch/Fetch';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';

const Form = () => {
  const dispatch = useDispatch();
  const errormessage = useSelector(
    (state) => state.userReducer.errormessageRegister
  );
  const [userName, setUserName] = useState(null);
  const [password, setPassword] = useState(null);
  const [submitted, setSubmitted] = useState(false);

  function handleUsernameChange(event) {
    setUserName(event.target.value.trim());
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value.trim());
  }

  async function handleSubmit(event) {
    event.preventDefault();
    if (!userName && !password) {
      setUserName(userName);
      setPassword(password);
      return dispatch({ type: 'REGISTER_MISSING_PASSWORD_AND_USERNAME' });
    } else if (!userName) {
      setUserName(userName);
      setPassword(password);
      return dispatch({ type: 'REGISTER_MISSING_USERNAME' });
    } else if (!password) {
      setUserName(userName);
      setPassword(password);
      return dispatch({ type: 'REGISTER_MISSING_PASSWORD' });
    } else if (password.length < 8) {
      setUserName(userName);
      setPassword(password);
      return dispatch({ type: 'PASSWORD_UNDER_8_CHARACTERS' });
    }

    setSubmitted(true);
  }

  useEffect(() => {
    if (submitted) {
      let body = {
        username: userName,
        password: password,
      };
      Fetch('POST', '/register', body)
        .then((response) => {
          return dispatch({ type: 'CLEAR_FIELDS' });
        })
        .catch((error) => {
          setUserName(userName);
          setPassword(password);
          return dispatch({
            type: 'REGISTER_ERROR',
            errormessage: error.toString(),
          });
        });
    }
  }, [submitted]);

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <InputField
          onChange={handleUsernameChange}
          placeholder='Username'
          name='username'
          id='username'
          type='text'
        />
        <InputField
          onChange={handlePasswordChange}
          placeholder='Password'
          name='password'
          id='password'
          type='password'
        />
        <br></br>
        {errormessage && <span> {errormessage}</span>}
        <br></br>
        <button type='submit'>Sign Up</button>
      </form>
    </div>
  );
};

export default Form;
