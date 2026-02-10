export interface Product {
    id: number;
    title: string;
    price: number;
    category?: {
        id: number;
        title: string;
    };
}

export interface Cart {
    id: number;
    products: Product[];
}

export interface Order {
    id: number;
    userId: number;
    items: OrderItem[];
    totalPrice: number;
}

export interface OrderItem {
    id: number;
    product: Product;
    quantity: number;
    price: number;
}
