import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../../services/api';
import { AiFillEdit, AiTwotoneDelete } from "react-icons/ai";
import {toast} from "react-toastify";


export const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await api.get('product/getAll');
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    const handleDelete = async (productId) => {
        try {
            await api.delete(`product/delete/${productId}`);
            toast.success("Deleted Successfully");
            fetchProducts(); // Refresh product list after deletion
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return (
        <div style={{ width: '80%', margin: '0 auto' }}>
            <h2>Product List</h2>
            <table style={{ borderCollapse: 'collapse', width: '100%' }}>
                <thead>
                <tr>
                    <th style={{ border: '1px solid #ddd', padding: '8px' }}>Product Name</th>
                    <th style={{ border: '1px solid #ddd', padding: '8px' }}>Price</th>
                    <th style={{ border: '1px solid #ddd', padding: '8px' }}>Category</th>
                    <th style={{ border: '1px solid #ddd', padding: '8px', maxWidth: '200px' }}>Description</th>
                    <th style={{ border: '1px solid #ddd', padding: '8px' }}>Actions</th>
                </tr>
                </thead>
                <tbody>
                {products.map((product) => (
                    <tr key={product.productId}>
                        <td style={{ border: '1px solid #ddd', padding: '8px' }}>{product.productName}</td>
                        <td style={{ border: '1px solid #ddd', padding: '8px' }}>{product.productPrice}</td>
                        <td style={{ border: '1px solid #ddd', padding: '8px' }}>{product.productCategory}</td>
                        <td style={{ border: '1px solid #ddd', padding: '8px', maxWidth: '200px', overflow: 'hidden', textOverflow: 'ellipsis' }}>{product.productDescription}</td>
                        <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'center' }}>
                            <Link to={`/update-product/${product.productId}`}>
                                <AiFillEdit size={24} style={{ marginRight: '8px' }} />
                            </Link>
                            <button onClick={() => handleDelete(product.productId)}>
                                <AiTwotoneDelete size={24} />
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};
