import { useState, useEffect } from 'react';
import "./App.css"

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [showProducts, setShowProducts] = useState(false);

  useEffect(() => {
    // La función que obtiene los productos de la URL
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8081/products');
        if (response.ok) {
          const data = await response.json();
          setProducts(data);
        } else {
          console.error('Error al obtener los productos');
        }
      } catch (error) {
        console.error('Error al obtener los productos', error);
      }
    };

    if (showProducts) {
      fetchData();
    }
  }, [showProducts]);

  return (
    <div>
      <button onClick={() => setShowProducts(true)}>Mostrar Productos</button>
      {showProducts && products.length > 0 && (
        <div>
          <h2>Lista de Productos</h2>
          <ul>
            {products.map((product) => (
              <li key={product.id}>
                <h3>{product.name}</h3>
                <p>Precio: {product.price}</p>
                <p>Descripción: {product.description}</p>
                <p>Stock: {product.stock}</p>
                <p>Tipo de Producto: {product.productType.description}</p>
                <p>Marca: {product.brand.description}</p>
                <p>Especificaciones:</p>
                <ul>
                  {product.specs.map((spec) => (
                    <li key={spec.id}>{spec.description}</li>
                  ))}
                </ul>
                <p>Imágenes:</p>
                <div>
                  {product.productImages.map((image) => (
                    <img
                      key={image.id}
                      src={`data:image/jpeg;base64, ${image.productImage}`}
                      alt="Product"
                    />
                  ))}
                </div>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
};

export default ProductList;




