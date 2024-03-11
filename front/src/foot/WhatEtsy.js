import React from 'react';


const WhatEtsy = () => {
    const WhatEtsyStyle = {
        backgroundColor: '#F0E68C',
        padding: '40px',
        textAlign: 'center',
    };

    const h2_style = {
        fontSize: '20px',
        margin: '10px 0',
    };

    const p_style = {
        fontSize: '50px',
        margin: '0px 0',
    };

    const p1_style = {
        fontSize: '15px',
        margin: '0',
    };

    const p2_2style = {
        fontSize: '15px',
        margin: '10px 0', 
    };

    const p3_style = {

    };

    return (
        <div style={WhatEtsyStyle}>
            <h2 style={p_style}>What is Etsy?</h2>
            <h2 style={p1_style}>Read our wonderfully weird story</h2>
            <br />
            <br />
            <div style={{ display: 'flex', justifyContent: 'center' }}>
                <div style={{ marginRight: '20px' }}>
                    <h2 style={h2_style}>A community doing good</h2>
                    <p style={p2_2style}>Etsy is a global online marketplace, where people
                    <br />
                        come together to make, sell, buy, and collect
                        <br />
                        unique items. We’re also a community pushing for
                        <br />
                        positive change for small businesses, people, and
                        <br />
                        the planet. Here are some of the ways we’re making
                        <br />
                        a positive impact, together.</p>
                </div>

                <div style={{ marginRight: '20px' }}>
                    <h2 style={h2_style}>Support independent creators</h2>
                    <p style={p2_2style}>There’s no Etsy warehouse - just millions of people
                    <br />
                        selling the things they love. We make the whole
                        <br />
                        process easy, helping you connect directly with 
                        <br />
                        makers to find something extraordinary.</p>
                </div>

                <div>
                    <h2 style={h2_style}>Peace of mind</h2>
                    <p style={p2_2style}>Your privacy is the highest priority of our dedicated 
                    <br />
                        team. And if you ever need assistance, we are 
                        <br />
                        always ready to step in for support.</p>
                </div>
            </div>
            <br />
            <p style={p3_style}>Have a question? Well, we’ve got some answers.</p>
            <p style={p3_style}>Go to Help Center</p>

        </div>
    );
};

export default WhatEtsy;
