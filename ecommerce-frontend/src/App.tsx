import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar.tsx";
import ProductsPage from "./components/ProductPage/ProductsPage.tsx";
import CartPage from "./components/CartPage/CartPage.tsx";
import OrdersPage from "./components/OrdersPage";

export default function App() {
    return (
        <Router>
            <Navbar />
            <div style={{ padding: "1rem" }}>
                <Routes>
                    <Route path="/" element={<Navigate to="/products" />} />
                    <Route path="/products" element={<ProductsPage />} />
                    <Route path="/cart" element={<CartPage />} />
                    <Route path="/orders" element={<OrdersPage />} />
                </Routes>
            </div>
        </Router>
    );
}
