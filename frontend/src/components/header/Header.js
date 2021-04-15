import React, { useEffect, useState } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import './Header.css';

function Header() {
  const dispatch = useDispatch();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const kingdomName = useSelector(state => state.userReducer.kingdomName);

  const tokenCheck = () => {
    if (localStorage.getItem('token')) {
      setIsLoggedIn(true);
      return dispatch({
        type: 'SET_NEW_KINGDOMNAME',
        kingdomName: kingdomName,
      });
    } else {
      setIsLoggedIn(false);
      return dispatch({
        type: 'SET_NEW_KINGDOMNAME',
        kingdomName: 'Tribes of Gymnocercus',
      });
    }
  };

  useEffect(() => {
    tokenCheck();
  }, []);

  const user = (
    <div className="header-container">
      <Link to={'/feed'} className="kingdomNameLink">
        <h1 className="header-title">FEED</h1>
      </Link>
      <div className="RightButtonsContainer">
        <NavLink
          to={'/create'}
          className="buttonLink"
          activeClassName="selected"
        >
          <div className="header-button-container">
            <p className="header-button-text">New Meme</p>
          </div>
        </NavLink>
        <Link
          to={'/login'}
          className="buttonLink"
          onClick={() => {
            localStorage.removeItem('token');
            dispatch({ type: 'CLEAR_ALL' });
          }}
        >
          <div className="header-button-container">
            <p className="header-button-text">{'Logout'}</p>
          </div>
        </Link>
      </div>
    </div>
  );

  const guest = (
    <div className="header-container">
      <div className="kingdomNameLink">
        <img className="icon" src="https://t4.ftcdn.net/jpg/03/44/14/45/360_F_344144522_ZwgzA1OvEuZMUjtUgJKi5rVj2bPIGEYg.jpg"></img>
        <h1 className="header-title"></h1>
      </div>
      <div className="RightButtonsContainer">
        <Link to={'/feed'} className="buttonLink">
          <div className="header-button-container">
            <p className="header-button-text">Feed</p>
          </div>
        </Link>
        <Link to={'/creator'} className="buttonLink">
          <div className="header-button-container">
            <p className="header-button-text">New Meme</p>
          </div>
        </Link>
      </div>
    </div>
  );

  return isLoggedIn ? user : guest;
}

export default Header;