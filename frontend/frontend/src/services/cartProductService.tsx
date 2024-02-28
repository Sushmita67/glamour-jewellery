import { useState } from "react";
import { useAuthContext } from "../hooks/useAuthContext";
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { api } from "./api";
import {getLocalStorageItem} from "../utils/localStorageActions";

export const cartProductService = () => {
  const { token } = useAuthContext();
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const user = getLocalStorageItem("id") || 0; // Set userID to 0 if it's null

  async function addProductOnCart(productID: string | undefined, userID: string) {
    // setLoading(true);
    // try {
    //   await api.post(`/carts/`, { user: userID, products: [{ productId: productID, quantity: 1 }] }, {
    //     headers: {
    //       "Authorization": token,
    //     }
    //   });
    // } catch (error) {
    //   setError(error.response.data.message || requestErrorMessages.genericError);
    // }
    // setLoading(false);
  }

  async function fetchProductsOnCart(userID: string) {
    console.log('User ID:', user); // Log the userID

    setLoading(true);
    try {
      const response = await api.get(`/carts/${user}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });

      console.log('Response Data::::',response.data);
      setLoading(false);
      return response.data;
    } catch (error) {
      setError(error.response.data.message || requestErrorMessages.genericError);
    }
    setLoading(false);
  }

  async function removeProductFromCart(cartID: string | undefined) {
    // setLoading(true);
    // try {
    //   await api.delete(`/carts/${cartID}`, {
    //     headers: {
    //       "Authorization": token,
    //     }
    //   });
    // } catch (error) {
    //   setError(error.response.data.message || requestErrorMessages.genericError);
    // }
    // setLoading(false);
  }

  return {
    addProductOnCart,
    fetchProductsOnCart,
    removeProductFromCart,
    loading,
    error
  };
};
