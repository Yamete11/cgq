import { Link, useLocation } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
    const location = useLocation();

    const links = [
        { path: "/products", label: "Products" },
        { path: "/cart", label: "Cart" },
        { path: "/orders", label: "Orders" },
    ];

    return (
        <nav className="navbar">
            <div className="navbar-container">
                {links.map((link) => (
                    <Link
                        key={link.path}
                        to={link.path}
                        className={`nav-link ${location.pathname === link.path ? "active" : ""}`}
                    >
                        {link.label}
                    </Link>
                ))}
            </div>
        </nav>
    );
}
