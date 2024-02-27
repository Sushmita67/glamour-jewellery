import React from "react";

const ImageSelector = ({ imageUrl, setImage }) => {
    const handleImageUpload = (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setImage(reader.result); // Set the image data to the state
            };
            reader.readAsDataURL(file);
        }
    };

    return (
        <div>
            <input
                type="file"
                accept="image/*"
                onChange={handleImageUpload}
                required
            />
            {imageUrl && <img src={imageUrl} alt="Product" style={{ maxWidth: "100px", maxHeight: "100px" }} />}
        </div>
    );
};

export default ImageSelector;
