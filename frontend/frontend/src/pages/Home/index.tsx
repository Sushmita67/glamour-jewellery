import { useEffect, useState } from "react";
import { PageTitle } from '../../components/PageTitle/index';
import { ProductCard } from '../../components/ProductCard';
import { Loading } from '../../components/Loading';
import { ErrorMessage } from '../../components/ErrorMessage';
import { BannerCarousel } from '../../components/BannerCarousel';
import styles from "./style.module.css";

export const Home = () => {
    const [products, setProducts] = useState<any[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);


    useEffect(() => {
        const fetchProducts = async () => {
            setLoading(true);
            setError(null);

            try {
                // Fetch products directly within the component
                const response = await fetch('http://localhost:8080/product/getAll');
                if (!response.ok) {
                    throw new Error('Failed to fetch products');
                }
                const data = await response.json();
                setProducts(data);
            } catch (error) {
                console.error('Error fetching products:', error);
                setError('Failed to fetch products');
            }

            setLoading(false);
        };

        fetchProducts();
    }, []);

    if (loading) {
        return (
            <main>
                <Loading />
            </main>
        );
    }

    if (error) {
        return (
            <main>
                <ErrorMessage className="text-center" message={error} />
            </main>
        );
    }

    // Log fetched products before return statement
    console.log('Fetched products:', products);

    return (
        <main className={`${styles.main} container-padding`}>
            <section className="max-width">
                <BannerCarousel />

                {error && <ErrorMessage className="text-center" message={error} />}
                {products.length > 0 ? (
                    <div className={styles.productsContainer}>
                        {products.map((product, index) => (
                            <div key={index} className={`${styles.productCardWrapper} ${styles.productCardSpacing}`}>
                                <ProductCard
                                    productId={product.productId}
                                    productCategory={product.productCategory}
                                    productName={product.productName}
                                    productImageUrl={product.image}
                                    productPrice={product.productPrice}
                                />
                            </div>
                        ))}
                    </div>
                ) : (
                    <ErrorMessage
                        className="text-center"
                        message="There are no products available now, please, come back later."
                    />
                )}
            </section>
        </main>

    );
};
