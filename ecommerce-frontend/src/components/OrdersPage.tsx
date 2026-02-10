import { useEffect, useState } from "react";
import type {Order} from "../types";
import { fetchOrders } from "../api/api";

const USER_ID = 1;

export default function OrdersPage() {
    const [orders, setOrders] = useState<Order[]>([]);

    useEffect(() => {
        fetchOrders(USER_ID).then(setOrders);
    }, []);

    return (
        <div>
            <h2>Orders</h2>
            {orders.length === 0 ? <p>No orders yet</p> : (
                <ul>
                    {orders.map(order => (
                        <li key={order.id}>
                            Order #{order.id} - Total: ${order.totalPrice.toFixed(2)}
                            <ul>
                                {order.items.map(item => (
                                    <li key={item.id}>
                                        {item.product.title} x {item.quantity} - ${item.price}
                                    </li>
                                ))}
                            </ul>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
