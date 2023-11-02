import React from "react";
import ReactDOM from "react-dom/client";
import ProductList from "./VerProducto.jsx";
import ProductForm from "./CargaProducto.jsx";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ProductForm />
    <ProductList />
  </React.StrictMode>
);
