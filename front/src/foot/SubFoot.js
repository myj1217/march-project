import React from 'react';


const SubFoot = () => {
    const subFootStyle = {
        backgroundColor: 'skyblue',
        height: '100px',
        margin: '0',
        padding: '40px 0',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    };

    const contentContainerStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    };

    const subP_style = {
        fontSize: '20px',
        textAlign: 'center',
    };

    const searchInputStyle = {
        padding: '10px',
        paddingRight: '50px',
        paddingLeft: '10px',
        paddingTop: '10px',
        width: '400px',
        border: '1px solid #000',
    };

    const War_style = {
        color: 'red',
    };

    return (
        <div style={subFootStyle}>
            <div style={contentContainerStyle}>
                <p style={subP_style}>Yes! Send me exclusive offers, unique gift ideas, and personalized tips for shopping and selling on Etsy.</p>
                <input type="text" placeholder="Enter Your Email" style={searchInputStyle} />
                <p style={War_style}>Please enter a valid email address.</p>
            </div>
        </div>
    );
};

export default SubFoot;
