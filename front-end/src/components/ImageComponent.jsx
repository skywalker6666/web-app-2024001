import React, { useEffect, useState } from 'react';

const ImageComponent = ({ imageUrl }) => {
    const [src, setSrc] = useState(imageUrl);

    useEffect(() => {
        setSrc(`${imageUrl}?${new Date().getTime()}`); // Append timestamp to force reload
    });

    return (
        <div align='center'>
            <img src={src} alt="image" className='image' />
        </div>
    );
};

export default ImageComponent;
