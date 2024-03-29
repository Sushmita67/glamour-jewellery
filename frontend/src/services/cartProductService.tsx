import { useState } from "react";
import { useAuthContext } from "../hooks/useAuthContext";
import { ProductsDataType } from "../interfaces/Products";
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { getLocalStorageItem } from './../utils/localStorageActions';
import { api } from "./api";


export const cartProductService = () => {

  const userID = getLocalStorageItem("id");

  const { token } = useAuthContext();
  const [productsOnCart, setProductsOnCart] = useState<ProductsDataType | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);


  function addProductOnCart(productID: string | undefined, status: string) {

    try {

      api.post(`user/addToCart/userId=${userID}&productId=${productID}`, {}, {
        headers: {
          "Authorization": token,
        }
      });

    } catch (error) { }
  }


  function removeProductFromCart(productID: string | undefined) {

    try {

      api.delete(`user/deleteFromCart/userId=${userID}&productId=${productID}`, {
        headers: {
          "Authorization": token,
        }
      });

    } catch (error) { }

  }


  function removeAllProductsFromCart() {

    try {

      api.delete(`user/clearCart/userId=${userID}`, {
        headers: {
          "Authorization": token,
        }
      });

    } catch (error) { }
  }


  async function fetchProductsOnCart() {

    setLoading(true);
    setError(null);

    try {

      const response: any = await api.get(`user/fetchCartItemsByUser/${userID}`, {
        headers: {
          'Authorization': `Bearer ${token}`}
      });

      setProductsOnCart(response);
      console.log('Fetched Cart Items:::',response);

    } catch (error) {

      setLoading(false);
      setError(requestErrorMessages.genericError);

    }

    setLoading(false);
  }


  return ({
    addProductOnCart,
    removeProductFromCart,
    removeAllProductsFromCart,
    fetchProductsOnCart,
    productsOnCart,
    loading,
    error
  });
}
