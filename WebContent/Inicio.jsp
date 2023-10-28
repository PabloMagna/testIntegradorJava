<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cliente.TipoCliente" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@include file="Layout.jsp" %>
<h2>Página Principal</h2>

<%
if (cliente == null) {
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




