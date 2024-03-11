import React from 'react';

const Et = () => {

    const Et_style = {
        backgroundColor: '#6495ED',
        height: '30px',
        margin: '0',
        padding: '40px 0',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    };

    const et_style = {
        color: 'white',
        fontSize: '15px',
    }; 

    return (
        <div style={Et_style}>
            <h2 style={et_style}>Etsy is powered by 100% renewable electricity.</h2>
        </div>
    );
};

export default Et;