<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
// Obtén la sesión actual
HttpSession ses = request.getSession(false); // El argumento "false" evita la creación de una nueva sesión si no existe.
Cliente cliente = (Cliente) ses.getAttribute("cliente");
%>

<!-- Contenido que se mostrará sin importar si el usuario está logueado o no -->
<h2>Página Principal</h2>

<%
if (cliente != null) {
%>
    <!-- El usuario está logueado -->
    <h2>Logueado como: <%= cliente.getUsuario() %> (<%= cliente.getUsuario() %>)</h2>
    <p><a href="CerrarSesion">Cerrar Sesión</a></p>
<%
} else {
%>
    <!-- El usuario no está logueado, muestra el formulario de inicio de sesión -->
    <h2>Inicio de Sesión</h2>
    <form id="loginForm" action="ServletCliente" method="POST">
        <label for="username">Usuario:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" name="btnLogin" value="Iniciar Sesión">
    </form>
    <p id="message"></p>
<%
}
%>

<!-- Enlaces para listar clientes y agregar un nuevo cliente -->
<p><a href="ServletCliente?lista=1">Listar Clientes</a></p>
<p><a href="AltaCliente.jsp">Agregar Nuevo Cliente</a></p>
