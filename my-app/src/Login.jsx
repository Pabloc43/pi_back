import { useState } from 'react';

const LoginComponent = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleLogin = async (token) => {
    // Almacenar el token JWT en el almacenamiento local
    localStorage.setItem('jwtToken', token);

    // Redirigir a la página principal o realizar otra acción después del inicio de sesión
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Enviar los datos del formulario al backend (usando fetch o axios)
      const response = await fetch('http://localhost:8081/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        const { token } = data;

        // Llamar a la función para manejar el inicio de sesión y almacenar el token
        handleLogin(token);
      } else {
        // Manejar errores, por ejemplo, mostrar un mensaje de error al usuario
      }
    } catch (error) {
      console.error('Error en el inicio de sesión:', error);
    }
  };

  return (
    <div>
      <h2>Iniciar Sesión</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Usuario:</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Contraseña:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Iniciar Sesión</button>
      </form>
    </div>
  );
};

export default LoginComponent;
