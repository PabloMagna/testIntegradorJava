<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cliente.TipoCliente" %>
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
    int clienteID = cliente.getIdCliente(); // Obtiene el ID del cliente desde la sesión
    TipoCliente tipoCliente = cliente.getTipoCliente(); // Obtiene el tipo de usuario (0 para CLIENTE, 1 para ADMIN)
%>
    <!-- El usuario está logueado -->
    <h2>Logueado como: <%= cliente.getUsuario() %> (<%= cliente.getUsuario() %>)</h2>
    <form id="logoutForm" action="ServletCliente" method="POST">
        <input type="submit" name="btnCerrarSesion" value="Cerrar Sesión">
    </form>
    <%
    if (tipoCliente == TipoCliente.ADMIN) { // Verifica si el usuario es un admin (tipo 1)
    %>
    <!-- Enlaces adicionales solo para usuarios administradores -->
    <p><a href="ServletCliente?lista=1">Listar Clientes</a></p>
    <p><a href="ServletCliente?alta=1">Agregar Cliente</a></p>
    <p><a href="ServletPrestamo?lista=1">Listar Prestamos admin</a></p>
     <p><a href="ServletCuenta?lista=1">Listar Cuentas</a></p>
    <%
    }else{%>
    <!-- Enlace para listar cuentas específico para clientes -->
    <p><a href="ServletCuenta?listaPorId=1">Listar Cuentas</a></p>
    <p><a href="ServletPrestamo?listaPagar=1">Pagar Préstamos</a></p>
    <p><a href="DetalleCliente.jsp">Datos Personales</a></p>

<%
} } else {
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




