import React from 'react';

const OtherHeader = () => {
  const menuItems = ["Shop Deals", "Home Favorites", "Fashion Finds", "Gift Guides", "Registry"];

  const containerStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '10px 20px',
  };

  const menuItemStyle = {
    fontSize: '14px',
    margin: '0 50px',
    cursor: 'pointer',
  };

  return (
    <div style={containerStyle}>
      {menuItems.map((item, index) => (
        <div key={index} style={menuItemStyle}>{item}</div>
      ))}
    </div>
  );
};

export default OtherHeader;
