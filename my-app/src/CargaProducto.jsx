import { useState, useEffect } from 'react';

function ProductForm() {
  const [formData, setFormData] = useState({
    name: '',
    price: 0,
    description: '',
    stock: 0,
    productType: 0,
    brand: 0,
    specs: [],
    productImages: [],
  });

  const [productTypes, setProductTypes] = useState([]);
  const [brands, setBrands] = useState([]);
  const [specs, setSpecs] = useState([]);

  useEffect(() => {
    // Cargar las opciones de productType desde localhost:8081/productTypes
    fetch('http://localhost:8081/productTypes')
      .then((response) => response.json())
      .then((data) => {
        setProductTypes(data);
      })
      .catch((error) => {
        console.error('Error al cargar productTypes', error);
      });

    // Cargar las opciones de brand desde localhost:8081/brands
    fetch('http://localhost:8081/brands')
      .then((response) => response.json())
      .then((data) => {
        setBrands(data);
      })
      .catch((error) => {
        console.error('Error al cargar brands', error);
      });

    // Cargar las opciones de specs desde localhost:8081/specs
    fetch('http://localhost:8081/specs')
      .then((response) => response.json())
      .then((data) => {
        setSpecs(data);
      })
      .catch((error) => {
        console.error('Error al cargar specs', error);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleCheckboxChange = (e) => {
    const { name, value } = e.target;
    const specId = parseInt(value); // Convierte el valor a entero

    if (e.target.checked) {
      const updatedSpecs = [...formData.specs, { id: specId }];
      setFormData({ ...formData, [name]: updatedSpecs });
    } else {
      const updatedSpecs = formData.specs.filter((spec) => spec.id !== specId);
      setFormData({ ...formData, [name]: updatedSpecs });
    }
  };

  const handleFileChange = (e) => {
    const files = e.target.files;
    if (files.length > 0) {
      const filePromises = Array.from(files).map((file) => {
        return new Promise((resolve) => {
          const reader = new FileReader();
          reader.onload = (event) => {
            const base64Image = event.target.result.split(',')[1];
            resolve(base64Image);
          };
          reader.readAsDataURL(file);
        });
      });

      Promise.all(filePromises).then((base64Images) => {
        setFormData({
          ...formData,
          productImages: [...formData.productImages, ...base64Images],
        });
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Construir el objeto productData a partir de formData
    const productData = {
      name: formData.name,
      price: formData.price,
      description: formData.description,
      stock: formData.stock,
      productType: { id: formData.productType },
      brand: { id: formData.brand },
      specs: formData.specs.map((spec) => ({ id: spec.id })),
      productImages: formData.productImages.map((image) => ({ productImage: image })),
    };

    console.log(productData);

    try {
      // Realizar la petición POST
      const response = await fetch('http://localhost:8081/products/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(productData),
      });

      if (response.ok) {
        // La petición fue exitosa, puedes manejar la respuesta aquí.
        console.log('Producto creado con éxito');
      } else {
        // Manejar errores en la respuesta aquí.
        console.error('Error al crear el producto');
      }
    } catch (error) {
      // Manejar errores de red aquí.
      console.error('Error de red', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Nombre del producto:</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Precio:</label>
        <input
          type="number"
          name="price"
          value={formData.price}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Descripción:</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Stock:</label>
        <input
          type="number"
          name="stock"
          value={formData.stock}
          onChange={handleChange}
        />
      </div>

      <div>
        <label>Producto Type:</label>
        <select
          name="productType"
          value={formData.productType}
          onChange={handleChange}
        >
          <option value={0}>Seleccione una opción</option>
          {productTypes.map((type) => (
            <option key={type.id} value={type.id}>
              {type.description}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Marca:</label>
        <select
          name="brand"
          value={formData.brand}
          onChange={handleChange}
        >
          <option value={0}>Seleccione una opción</option>
          {brands.map((brand) => (
            <option key={brand.id} value={brand.id}>
              {brand.description}
            </option>
          ))}
        </select>
      </div>
      <div>
        <label>Especificaciones:</label>
        {specs.map((spec) => (
          <div key={spec.id}>
            <label>
              <input
                type="checkbox"
                name="specs"
                value={spec.id}
                checked={formData.specs.some((s) => s.id === spec.id)}
                onChange={handleCheckboxChange}
              />
              {spec.description}
            </label>
          </div>
        ))}
      </div>
      <div>
        <label>Seleccionar imágenes:</label>
        <input type="file" accept="image/*" onChange={handleFileChange} multiple />
      </div>
      <button type="submit">Crear Producto</button>
    </form>
  );
}

export default ProductForm;
