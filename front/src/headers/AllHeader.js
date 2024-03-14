import React, { useState } from 'react';
import estylogo from './imgs/etsy.png';
import categorylogo from './imgs/category.png';
import icon from './imgs/icon_magnifier_black.png';
import heartlogo from './imgs/heart.png';
import bagunilogo from './imgs/baguni.png';

const AllHeader = () => {
  const [searchTerm, setSearchTerm] = useState('');

  const iconStyle = {
    width: '20px',
    height: '20px',
  };

  const containerStyle = {
    position: 'absolute',
    left: '500px',
    top: '50%',
    transform: 'translateY(-50%)',
  };

  const inputStyle = {
    padding: '10px',
    paddingRight: '50px',
    paddingLeft: '10px',
    paddingTop: '19px',
    width: '800px',
    border: '2px solid #000',
  };
  
  

  const headerStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '10px 277px',
  };

  const logoStyle = {
    width: 'auto',
    maxWidth: '100px',
    maxHeight: '60px',
    marginBottom: '1px',
  };

  const categoryLogoStyle = {
    width: 'auto',
    maxWidth: '100px',
    maxHeight: '40px',
    marginRight: '1180px',
    cursor: 'pointer',
  };

  const signUpStyle = {
    cursor: 'pointer',
    position: 'relative',
    display: 'inline-block',
    marginLeft: '20px',
  };

  const heartLogoStyle = {
    cursor: 'pointer',
    width: 'auto',
    maxWidth: '100px',
    maxHeight: '50px',
    marginLeft: '60px',
  };
  
  const baguniLogoStyle = {
    cursor: 'pointer',
    width: 'auto',
    maxWidth: '100px',
    maxHeight: '50px',
    marginLeft: '60px',
    paddingTop: '10px',
  }


  return (
    <div style={{ position: 'relative' }}>
      <div style={headerStyle}>
        <img src={estylogo} alt="esty" style={logoStyle} />
        <img src={categorylogo} alt="category" style={categoryLogoStyle} />
        <div style={containerStyle}>
          <input type="text" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} style={inputStyle} />
          <button style={{ background: 'none', border: 'none', cursor: 'pointer', marginLeft: '-30px' }}>
            <img src={icon} alt="검색" style={iconStyle} />
          </button>
          <div style={signUpStyle}>
            Sign up
          </div>
        <img src={heartlogo} alt="heart" style={heartLogoStyle} />
        <img src={bagunilogo} alt="baguni" style={baguniLogoStyle} />
        </div>
      </div>
    </div>
  );
};

export default AllHeader;
