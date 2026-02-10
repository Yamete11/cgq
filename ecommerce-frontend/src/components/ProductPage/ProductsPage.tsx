import { useEffect, useState } from "react";
import type { Product } from "../../types";
import { fetchProducts, addToCart } from "../../api/api";
import "./ProductsPage.css";

const CART_ID = 1;

export default function ProductsPage() {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        fetchProducts().then(setProducts);
    }, []);

    const handleAdd = (productId: number) => {
        addToCart(CART_ID, productId)
            .then(() => alert("Added to cart!"))
            .catch(() => alert("Failed to add to cart"));
    };

    return (
        <div className="products-page">
            <div className="products-grid">
                {products.map((p) => (
                    <div key={p.id} className="product-card">
                        <div className="product-title">{p.title}</div>
                        <div className="product-price">${p.price.toFixed(2)}</div>
                        <button className="add-btn" onClick={() => handleAdd(p.id)}>
                            Add to Cart
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}
