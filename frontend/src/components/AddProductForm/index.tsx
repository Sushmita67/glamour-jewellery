import { FormEvent, useState } from "react";
import { Link } from "react-router-dom";

import { Label } from "../Label";
import { Input } from "../Input";
import { LoadingScreen } from "../LoadingScreen";
import { ErrorMessage } from "../ErrorMessage";
import { FormButton } from "../FormButton";

import { ProductType } from '../../interfaces/Products';
import { addProductService } from '../../services/addProductService';

import styles from "./style.module.css";
import {useAuthContext} from "../../hooks/useAuthContext";
import {api} from "../../services/api";
import {toast} from "react-toastify";

export const AddProductForm = () => {
    const { addProduct, loading, error } = addProductService();

    const [productName, setProductName] = useState<string>("");
    const [productPrice, setProductPrice] = useState<string>("");
    const [productCategory, setProductCategory] = useState<string>("");
    const [productImageUrl, setProductImageUrl] = useState<string>("");
    const [productDescription, setProductDescription] = useState<string>("");
    const [image, setImage] = useState(null); // State variable to hold the image file

    const [stockQuantity, setStockQuantity] = useState<number>(0);

    const { token } = useAuthContext();


    async function handleSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();

        const formData = new FormData();
        formData.append('productName', productName);
        formData.append('productPrice', parseFloat(productPrice).toString());
        formData.append('productCategory', productCategory);
        formData.append('productDescription', productDescription);
        formData.append('stockQuantity', stockQuantity.toString());
        formData.append('image', image); // Append the image file to the FormData

        try {
            // Send POST request with FormData
            await api.post(`product/save`, formData, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data', // Set content type to multipart/form-data for file upload
                }
            });
            toast.success("Product Added Successfully!");
            // Reset form fields on success
            setProductName("");
            setProductPrice("");
            setProductCategory("");
            setProductDescription("");
            setStockQuantity(0);
            setImage(null);
        } catch (error) {
            toast.error("Product Add Error! Ensure Image Filename is NOT a duplicate.");
            console.error('Error adding product:', error);
            // setError("Failed to add product. Please try again later.");
        }
    }

    // Function to handle image upload
    const handleImageUpload = (event) => {
        setImage(event?.target?.files[0]); // Set the image file to the state variable
    };

    return (
        <form onSubmit={handleSubmit}>
            {loading && <LoadingScreen />}
            <Label label="Product name">
                <Input
                    type="text"
                    value={productName}
                    setState={setProductName}
                    placeholder="Your product name"
                    required={true}
                />
            </Label>
            <Label label="Price">
                <Input
                    type="number"
                    value={productPrice}
                    setState={setProductPrice}
                    placeholder="Product price"
                    required={true}
                />
            </Label>
            <Label label="Category">
                <select
                    className={styles.select}
                    value={productCategory}
                    onChange={(e) => setProductCategory(e.target.value)}
                    required
                >
                    <option value="">Please choose a category</option>
                    <option value="Earring">Earring</option>
                    <option value="Bracelet">Bracelet</option>
                    <option value="Necklace">Necklace</option>
                    <option value="Ring">Ring</option>
                </select>
            </Label>
            <Label label="Product image">
                <div> {/* Wrap both input and img inside a div */}
                    <input
                        type="file"
                        accept="image/*"
                        onChange={handleImageUpload}
                        required
                    />
                    {productImageUrl && (
                        <img
                            src={productImageUrl}
                            alt="Product"
                            style={{ maxWidth: "100px", maxHeight: "100px" }}
                        />
                    )}
                </div>
            </Label>

            <Label label="Description">
                <Input
                    type="text"
                    value={productDescription}
                    setState={setProductDescription}
                    placeholder="Describe your product"
                    required={true}
                    maxLength={240}
                />
            </Label>
            <div className="form-actions">
                {error && <ErrorMessage message={error} />}
                <Link to="/">Go home</Link>
                <FormButton>Add product</FormButton>
            </div>
        </form>
    );
};
