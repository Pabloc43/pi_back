import React from "react";
import ReactDOM from "react-dom/client";
import ProductList from "./VerProducto.jsx";
import ProductForm from "./CargaProducto.jsx";
import RegisterComponent from "./Register.jsx";
import LoginComponent from "./Login.jsx";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ProductForm />
    <ProductList /> 
    <LoginComponent/>
    <RegisterComponent/>
    
  </React.StrictMode>
);
