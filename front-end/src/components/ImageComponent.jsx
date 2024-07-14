import React from 'react';

const ImageComponent = ({ imageUrl }) => {
  return (
    <div align='center'>

      <img src={imageUrl} alt="image" className='image' />
    </div>
  );
};

export default ImageComponent;
