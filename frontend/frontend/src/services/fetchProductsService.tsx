import { useState, useEffect } from "react";
import { ProductsWithCategoryType } from '../interfaces/Products';
import { requestErrorMessages } from "../constants/requestErrorMessages";
import { api } from "./api";


export const fetchProductsService = () => {

  const [products, setProducts] = useState<ProductsWithCategoryType | null>(null);

  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  async function fetchProducts() {

    setLoading(true);
    setError(null);

    try {

      const response: any = await api.get(`http://localhost:8080/product/getAll`);
      setProducts(response.data);
      console.log('Fetched Product Service:::',response.data);

    } catch (error) {

      setLoading(false);
      console.log('Generic Error');
      setError(requestErrorMessages.genericError);
    }

    setLoading(false);
  }

  useEffect(() => {

    fetchProducts();

  }, []);

  return ({ products, loading, error })
}
