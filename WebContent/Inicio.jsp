<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Inicio de Sesión</title>
</head>
<body>
    <h2>Inicio de Sesión</h2>
    <form id="loginForm" action="ServletCliente" method="POST">
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Iniciar Sesión">
    </form>

    <p id="message"></p>

</body>
</html>
