import React from 'react';

const LastF = () => {
  const LastStyle = {
    backgroundColor: 'black',
    padding: '10px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    color: 'white',
  };

  const combinedTextStyle = {
    marginLeft: '150px',
    display: 'flex',
  };

  const p_style = {
    margin: '0 20px',
    marginRight: '70px',
  };

  const combinedText = "South Korea | English (US) | $ (USD)";

  return (
    <div style={LastStyle}>
      <span style={combinedTextStyle}>{combinedText}</span>
      <div style={combinedTextStyle}>
        <p style={p_style}>@2024 Etsy, Inc.</p>
        <p style={p_style}>Terms of Use</p>
        <p style={p_style}>Privacy</p>
        <p style={p_style}>Interest-based ads</p>
        <p style={p_style}>Local Shops</p>
        <p style={p_style}>Regions</p>
      </div>
    </div>
  );
};

export default LastF;
