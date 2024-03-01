import { useState } from 'react';
import { useAuthContext } from "../hooks/useAuthContext";
import { ProductsDataType } from '../interfaces/Products';
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { getLocalStorageItem } from '../utils/localStorageActions';
import { api } from './api';
import {useParams} from "react-router-dom";

export const favoriteProductService = () => {
  const userID = getLocalStorageItem("id");
  const { token } = useAuthContext();
  const [favoriteProducts, setFavoriteProducts] = useState<ProductsDataType | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const { productId } = useParams();
  function favoriteProduct(productID: string | undefined) {
    try {
      api.post(`/wishlist/save/${userID}/${productID}`, {}, { // Updated endpoint
        headers: {
          "Authorization": token,
        }
      });
    } catch (error) {
      console.error("Error adding product to favorites:", error);
    }
  }

  function removeFavoriteProduct(productID: string | undefined) {
    try {
      api.delete(`/user/favorite/${userID}/${productID}`, {
        headers: {Authorization:"Bearer "+token}
      });
    } catch (error) {
      console.error("Error removing product from favorites:", error);
    }
  }

  console.log('Setting Favorites:::',productId);

  async function fetchFavoriteProducts() {
    setLoading(true);
    setError(null);
    try {
      const response: any = await api.get(`/wishlist/getByUserAndProductId/${userID}/${productId}`, {
        headers: {
          'Authorization': `Bearer ${token}`}}); // Updated endpoint

      setFavoriteProducts(response.data[0]);
      console.log('fetchFavoriteProducts():::',response.data[0]);
    } catch (error) {
      setLoading(false);
      setError(requestErrorMessages.genericError);
    }
    setLoading(false);
  }

  return ({
    favoriteProduct,
    removeFavoriteProduct,
    fetchFavoriteProducts,
    favoriteProducts,
    loading,
    error
  });
}
