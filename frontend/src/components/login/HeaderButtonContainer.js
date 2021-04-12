import React from 'react';
import './HeaderButtonContainer.css';
import HeaderButton from './HeaderButton';

const HeaderButtonContainer = () => {
    return(
        <div className="header-button-container-container">
            <HeaderButton headerButtonText='Login' />
            <HeaderButton headerButtonText='Register' />
        </div>
    )
}

export default HeaderButtonContainer;