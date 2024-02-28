import { useState } from "react";
import { ProductType } from '../interfaces/Products';
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { useAuthContext } from "../hooks/useAuthContext";
import { api } from "./api";


export const addProductService = () => {

  const { token } = useAuthContext();

  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  console.log("Token:",token);
  async function addProduct(data: ProductType) {

    setLoading(true);
    setError(null);

    try {

      console.log('service data:::',data);

      await api.post(`product/save`, data, {
        headers: {Authorization:"Bearer "+token}
      });

    } catch (error) {

      setLoading(false);
      setError(requestErrorMessages.genericError);
    }

    setLoading(false);
  }

  return ({ addProduct, loading, error })
}
