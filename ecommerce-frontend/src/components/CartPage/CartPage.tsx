import { useEffect, useState } from "react";
import type {Cart} from "../../types";
import { fetchCart, removeFromCart, createOrder } from "../../api/api.ts";

const CART_ID = 1;
const USER_ID = 1;

export default function CartPage() {
    const [cart, setCart] = useState<Cart>({ id: CART_ID, products: [] });

    const loadCart = () => fetchCart(CART_ID).then(setCart);

    useEffect(() => {
        loadCart();
    }, []);

    const handleRemove = (productId: number) => {
        removeFromCart(CART_ID, productId).then(loadCart);
    };

    const handleOrder = () => {
        const productIds = cart.products.map(p => p.id);
        createOrder(USER_ID, productIds).then(order => {
            alert(`Order created! Total: $${order.totalPrice}`);
            loadCart();
        });
    };

    return (
        <div>
            <h2>Cart</h2>
            {cart.products.length === 0 ? <p>Cart is empty</p> : (
                <ul>
                    {cart.products.map(p => (
                        <li key={p.id}>
                            {p.title} - ${p.price.toFixed(2)}
                            <button onClick={() => handleRemove(p.id)}>Remove</button>
                        </li>
                    ))}
                </ul>
            )}
            {cart.products.length > 0 && <button onClick={handleOrder}>Checkout</button>}
        </div>
    );
}
