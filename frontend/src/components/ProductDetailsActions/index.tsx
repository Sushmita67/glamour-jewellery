import React, { useState, useEffect } from 'react';
import { AiOutlineHeart } from "react-icons/ai";
import { HiOutlineShoppingCart } from "react-icons/hi";
import { favoriteProductService } from "../../services/favoriteProductService";
import { cartProductService } from '../../services/cartProductService';
import { ProductsDataType } from '../../interfaces/Products';
import { useAuthContext } from '../../hooks/useAuthContext';
import { useNavigate } from 'react-router-dom';
import styles from "./style.module.css";

interface Props {
  productID: string | undefined;
  favoriteProducts: ProductsDataType | null;
  productsOnCart: ProductsDataType | null;
  onSetIsProductAlreadyFavorited: (value: boolean) => void; // Callback function to set isProductAlreadyFavorited
}

const productFavorited = `${styles.productFavorited}`;
const unfavoriteProduct = `${styles.unfavoriteProduct}`;

const productOnCart = `${styles.productOnCart}`;
const productNotOnCart = `${styles.productNotOnCart}`;

export const ProductDetailsActions = ({ productID, favoriteProducts, productsOnCart, onSetIsProductAlreadyFavorited }: Props) => {
  const { token } = useAuthContext();
  const navigate = useNavigate();

  const { favoriteProduct, removeFavoriteProduct } = favoriteProductService();
  const { addProductOnCart, removeProductFromCart } = cartProductService();

  const [isProductAlreadyFavorited, setIsProductAlreadyFavorited] = useState<boolean>(false);
  const [isProductAlreadyOnCart, setIsProductAlreadyOnCart] = useState<boolean>(false);

  const userID = localStorage.getItem('id');


  function handleFavoriteActions() {
    if (!token) {
      navigate("/login");
      return;
    }

    if (isProductAlreadyFavorited) {
      removeFavoriteProduct(productID);
    } else {
      favoriteProduct(productID);
    }

    setIsProductAlreadyFavorited((prevState) => !prevState);
  }

  function handleCartActions() {
    if (!token) {
      navigate("/login");
      return;
    }

    if (isProductAlreadyOnCart) {
      removeProductFromCart(productID);
    } else {
      addProductOnCart(productID, status);
    }

    setIsProductAlreadyOnCart((prevState) => !prevState);
  }

  useEffect(() => {
    console.log('Favorite in Use Effect:::', favoriteProducts);
    if (favoriteProducts) {
      setIsProductAlreadyFavorited(true);
    } else {
      setIsProductAlreadyFavorited(false);
    }
  }, [favoriteProducts, onSetIsProductAlreadyFavorited]);


  useEffect(() => {
    if (productsOnCart && productsOnCart.data) {
      productsOnCart.data.forEach((product) => {
        product.productId?.toString() === productID && setIsProductAlreadyOnCart(true);
      });
    }
  }, [productsOnCart]);


  return (
      <div className={`${styles.container} max-width`}>
        <p>Product Details</p>
        <div className={styles.actions}>
          <button
              className={isProductAlreadyFavorited ? productFavorited : unfavoriteProduct}
              onClick={handleFavoriteActions}
          >
            <AiOutlineHeart />
          </button>
          <button
              className={isProductAlreadyOnCart ? productOnCart : productNotOnCart}
              onClick={handleCartActions}
          >
            <HiOutlineShoppingCart />
          </button>
        </div>
      </div>
  );
};
