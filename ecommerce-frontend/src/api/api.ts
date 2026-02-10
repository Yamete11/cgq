import type {Product, Cart, Order} from "../types";

const BASE_URL = "http://localhost:8080/api";

export const fetchProducts = async (): Promise<Product[]> => {
    const res = await fetch(`${BASE_URL}/products`);
    return res.json();
};

export const addToCart = async (cartId: number, productId: number): Promise<Cart> => {
    const res = await fetch(`${BASE_URL}/carts/${cartId}/add/${productId}`, {
        method: "POST",
    });
    return res.json();
};

export const removeFromCart = async (cartId: number, productId: number): Promise<Cart> => {
    const res = await fetch(`${BASE_URL}/carts/${cartId}/remove/${productId}`, {
        method: "POST",
    });
    return res.json();
};

export const fetchCart = async (cartId: number): Promise<Cart> => {
    const res = await fetch(`${BASE_URL}/carts/${cartId}`);
    return res.json();
};

export const createOrder = async (userId: number, productIds: number[]): Promise<Order> => {
    const res = await fetch(`${BASE_URL}/orders?userId=${userId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productIds),
    });
    return res.json();
};

export const fetchOrders = async (userId: number): Promise<Order[]> => {
    const res = await fetch(`http://localhost:8080/api/orders?userId=${userId}`);
    return res.json();
};
