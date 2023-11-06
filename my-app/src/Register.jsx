    import { useState } from 'react';

    const RegisterComponent = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstname: '',
        lastname: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
        // Enviar los datos del formulario al backend (usando fetch o axios)
        const response = await fetch('http://localhost:8081/auth/register', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            // Registro exitoso, redirigir a la página de inicio de sesión o realizar otra acción
        } else {
            // Manejar errores, por ejemplo, mostrar un mensaje de error al usuario
        }
        } catch (error) {
        console.error('Error en el registro:', error);
        }
    };

    return (
        <div>
        <h2>Registro</h2>
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
            <div>
            <label>Nombre:</label>
            <input
                type="text"
                name="firstname"
                value={formData.firstname}
                onChange={handleChange}
            />
            </div>
            <div>
            <label>Apellido:</label>
            <input
                type="text"
                name="lastname"
                value={formData.lastname}
                onChange={handleChange}
            />
            </div>
            <button type="submit">Registrarse</button>
        </form>
        </div>
    );
    };

    export default RegisterComponent;
