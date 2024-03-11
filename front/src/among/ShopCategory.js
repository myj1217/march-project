import React from "react";
import pop from './imgs/il_680x540.2507719880_5x9l.png';


const ShopCategory = () => {
    const containerStyle = {
        margin: '20px',
    };

    const estyShop = {
        textAlign: 'center',
        flexDirection: 'column',
    };

    const estyShop_1 = {
        fontSize: '35px',
        position: 'relative',
        top: '100px',
    };

    const estyShop_2 = {
        fontSize: '25px',

    };

    const imageStyle = {
        backdropFilter: 'saturate(300%) blur(20px)',
        width: '300px',
        height: '300px',
        marginBottom: '15px',
        marginTop: '100px',
        marginRight: '10px',
    };
    

    const shopStyle = {
        display: 'flex',
        justifyContent: 'space-around',
        alignItems: 'center',
        marginTop: '50px',
    };

    const allfont = {
        textAlign: 'center',
        fontSize: '18px',
        margin: '10px 0',
    };

    return (
        <div style={containerStyle}>
            <h2 style={estyShop}>
                <span style={estyShop_1}>Shop by Category</span>
            </h2>
            <br />
            
            <section id="shopList" style={shopStyle}>
                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>
                
                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>

                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>

                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>
            </section>

            <section id="shopList" style={shopStyle}>
                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>
                
                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>

                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>

                <div id="pop_List">
                    <img src={pop} alt="popL" style={imageStyle}></img>
                    <div style={allfont}>Art & Collectibles</div>
                </div>
            </section>

            <br />
            <br />
            <h2 style={estyShop}>
                <span style={estyShop_2}>Show more(5)</span>
            </h2>
        </div>
    );
};

export default ShopCategory;
