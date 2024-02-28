import React, { FormEvent, useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { Label } from "../Label";
import { Input } from "../Input";
import { LoadingScreen } from "../LoadingScreen";
import { ErrorMessage } from "../ErrorMessage";
import { FormButton } from "../FormButton";
import { useAuthContext } from "../../hooks/useAuthContext";
import { api } from "../../services/api";
import styles from "./style.module.css";
import { toast } from "react-toastify";
import ImageSelector from "../ImageSelector";

export const UpdateProductForm = () => {
    const { token } = useAuthContext();
    const { id } = useParams(); // Get the ID from the URL params
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [productName, setProductName] = useState("");
    const [productPrice, setProductPrice] = useState("");
    const [productCategory, setProductCategory] = useState("");
    const [productImageUrl, setProductImageUrl] = useState("");
    const [productDescription, setProductDescription] = useState("");
    const [stockQuantity, setStockQuantity] = useState(0);

    useEffect(() => {
        console.log('ID Operation',id);
        if (id) {
            // If ID is provided, fetch product data
            fetchProductData(id);
        }
    }, [id]);

    const fetchProductData = async (id) => {
        try {
            setLoading(true);
            const response = await api.get(`product/getById/${id}`);
            const productData = response.data;
            setProductName(productData.productName);
            setProductPrice(productData.productPrice);
            setProductCategory(productData.productCategory);
            setProductImageUrl(productData.productImageUrl);
            setProductDescription(productData.productDescription);
            setStockQuantity(productData.stockQuantity);
            setLoading(false);
        } catch (error) {
            toast.error("Error Fetching Product to Update!")
            console.error("Error fetching product:", error);
            setLoading(false);
            setError("Product doesn't exist! Please check ProductID");
        }
    };

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const updatedProduct = {
            productName,
            productPrice,
            productCategory,
            productImageUrl,
            productDescription,
            stockQuantity
        };

        try {
            setLoading(true);
            await api.put(`product/update/${id}`, updatedProduct, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setLoading(false);
            // Redirect or show success message
            toast.success("Product Updated Successfully!");
        } catch (error) {
            console.error("Error updating product:", error);
            setLoading(false);
            setError("Failed to update product. Please try again later.");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {loading && <LoadingScreen />}
            <Label label="Product name">
                <Input
                    type="text"
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                    placeholder="Your product name"
                    required={true}
                />
            </Label>
            <Label label="Price">
                <Input
                    type="number"
                    value={productPrice}
                    onChange={(e) => setProductPrice(e.target.value)}
                    placeholder="Product price"
                    required={true}
                />
            </Label>
            <Label label="Category">
                <Input
                    type="text"
                    value={productCategory}
                    onChange={(e) => setProductCategory(e.target.value)}
                    placeholder="Product category"
                    required={true}
                />
            </Label>
            <Label label="Product image">
                <ImageSelector
                    imageUrl={productImageUrl}
                    setImage={setProductImageUrl}
                />
            </Label>
            <Label label="Description">
                <Input
                    type="text"
                    value={productDescription}
                    onChange={(e) => setProductDescription(e.target.value)}
                    placeholder="Describe your product"
                    required={true}
                    maxLength={240}
                />
            </Label>
            <Label label="Stock Quantity">
                <Input
                    type="number"
                    value={stockQuantity}
                    onChange={(e) => setStockQuantity(Number(e.target.value))}
                    placeholder="Stock Quantity"
                    required={true}
                />
            </Label>
            <div className="form-actions">
                {error && <ErrorMessage message={error} />}
                <Link to="/">Go Home</Link>
                <FormButton>Update Product</FormButton>
            </div>
        </form>
    );
};
