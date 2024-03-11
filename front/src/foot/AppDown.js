import React from 'react';
import etsyimage from './imgs/etsyimage.png';


const AppDown = () => {
    const containerStyle = {
        backgroundColor: '#00BFFF',
        height: '350px',
        margin: '0',
        padding: '40px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const logoStyle = {
        width: '100px',
        height: 'auto',
        marginLeft: '500px',
        cursor: 'pointer',
    };

    const textContainerStyle = {
        display: 'flex',
        gap: '20px',
    };

    const sectionStyle = {
        marginRight: '150px',
        width: '120px',
    };

    const h2_style = {
        marginBottom: '0px',
    };

    const p_style = {
        margin: '10px',
        width: '120px',
    };

    return (
        <div style={containerStyle}>
            <img src={etsyimage} alt="Logo" style={logoStyle} />
            
            <div style={textContainerStyle}>
                <div style={sectionStyle}>
                    <h2 style={h2_style}>Shop</h2>
                    <br />
                    <div>
                        <p style={p_style}>Gift cards</p>
                        <p style={p_style}>Etsy Registry</p>
                        <p style={p_style}>Sitemap</p>
                        <p style={p_style}>Etsy blog</p>
                        <p style={p_style}>Etsy United Kingdom</p>
                        <p style={p_style}>Etsy Germany</p>
                        <p style={p_style}>Etsy Canada</p>
                    </div>
                </div>

                <div style={sectionStyle}>
                    <h2 style={h2_style}>Sell</h2>
                    <br />
                    <div>
                        <p style={p_style}>Sell on Etsy</p>
                        <p style={p_style}>Teams</p>
                        <p style={p_style}>Forums</p>
                        <p style={p_style}>Affiliates & Creators</p>
                    </div>
                </div>

                <div style={sectionStyle}>
                    <h2 style={h2_style}>About</h2>
                    <br />
                    <div>
                        <p style={p_style}>Etsy, Inc.</p>
                        <p style={p_style}>Policies</p>
                        <p style={p_style}>Investors</p>
                        <p style={p_style}>Careers</p>
                        <p style={p_style}>Press</p>
                        <p style={p_style}>Impact</p>
                        <p style={p_style}>Legal imprint</p>
                    </div>
                </div>

                <div style={sectionStyle}>
                    <h2 style={h2_style}>Help</h2>
                    <br />
                    <div>
                        <p style={p_style}>Help Center</p>
                        <p style={p_style}>Privacy settings</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AppDown;
