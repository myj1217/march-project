import React from 'react';
import PerLogo from './imgs/PersonalizedGifts.webp';
import WallLogo from './imgs/WallArt.webp';
import BeddLogo from './imgs/Bedding&Blankets.webp';
import JewLogo from './imgs/Jewelry.webp';
import TessLogo from './imgs/Tess&Sweaters.webp';
import SaleLogo from './imgs/OnSale.webp';

const HeaderText = () => {
  const containerStyle = {
    background: '#006400',
    color: '#ffffff',
    padding: '10px',
    textAlign: 'center',
    position: 'relative',
    height: '180px',
    marginBottom: '20px',
  };

  const listStyle = {
    display: 'flex',
    listStyle: 'none',
    padding: 0,
    position: 'absolute',
    left: '50%',
    transform: 'translateX(-50%)',
  };

  const listItemStyle = {
    marginRight: '20px',
  };

  const imageStyle = {
    width: '90px',
    height: '90px',
    borderRadius: '50%',
    cursor: 'pointer',
  };

  return (
    <div style={containerStyle}>
      <p style={{ fontSize: '38px', marginBottom: '20px' }}>Make it personal — shop custom gifts, decor, and on-sale finds now!</p>
      <ul style={listStyle}>
        <li style={listItemStyle}>
          <img src={PerLogo} alt="Per" style={imageStyle} />
          <p style={{ fontSize: '16px', color: 'black', marginTop: '10px' }}>Personalized Gifts</p>
        </li>
        <li style={listItemStyle}>
          <img src={WallLogo} alt="Wall" style={imageStyle} />
          <p style={{ fontSize: '16px', color: 'black', marginTop: '10px' }}>Wall Art</p>
        </li>
        <li style={listItemStyle}>
          <img src={BeddLogo} alt="Bed" style={imageStyle} />
          <p style={{ fontSize: '16px', color: 'black', marginTop: '10px' }}>Bedding & Blankets</p>
        </li>
        <li style={listItemStyle}>
          <img src={JewLogo} alt="Jew" style={imageStyle} />
          <p style={{ fontSize: '16px', color: 'black', marginTop: '10px' }}>Jewelry</p>
        </li>
        <li style={listItemStyle}>
          <img src={TessLogo} alt="Tess" style={imageStyle} />
          <p style={{ fontSize: '16px', color: 'black', marginTop: '10px' }}>Tess & Sweaters</p>
        </li>
        <li style={listItemStyle}>
          <img src={SaleLogo} alt="Sale" style={imageStyle} />
          <p style={{ fontSize: '16px',color: 'black', marginTop: '10px' }}>On Sale</p>
        </li>
      </ul>
    </div>
  );
};

export default HeaderText;
