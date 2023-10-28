<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.Cliente.TipoCliente" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Template</title>
    <link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
<%
// Obtén la sesión actual
HttpSession ses = request.getSession(false); // El argumento "false" evita la creación de una nueva sesión si no existe.
Cliente cliente = (Cliente) ses.getAttribute("cliente");
if (cliente != null) {
%>
<div id="titulo">
    <h1 class="h1-layout">
        <% if (cliente.getTipoCliente() == TipoCliente.ADMIN) { %>
            Área Administración
        <% } else { %>
            Área clientes
        <% } %>
    </h1>
</div>
<div id="menu">
    <ul class="ul-layout">
        <% if (cliente.getTipoCliente() == TipoCliente.CLIENTE) { %>
            <!-- Opciones para clientes no administradores -->
            <li><a href="ServletCuenta?listaPorId=1">Listar Cuentas</a></li>
            <li><a href="ServletPrestamo?listaPagar=1">Pagar Préstamos</a></li>
            <li><a href="DetalleCliente.jsp">Datos Personales</a></li>
        <% } else if (cliente.getTipoCliente() == TipoCliente.ADMIN) { %>
            <!-- Opciones para administradores -->
            <li><a href="ServletCliente?lista=1">Listar Clientes</a></li>
            <li><a href="ServletCliente?alta=1">Agregar Cliente</a></li>
            <li><a href="ServletPrestamo?lista=1">Listar Prestamos admin</a></li>
            <li><a href="ServletCuenta?lista=1">Listar Cuentas</a></li>
            <li><a href="Informe1.jsp">Informe 1</a></li>
            <li><a href="Informe2.jsp">Informe 2</a></li>
        <% } %>
       	 <li>
       		 <form id="logoutForm" action="ServletCliente" method="POST">
      			  <input type="submit" src="https://cdn-icons-png.flaticon.com/512/1427/1427087.png" name="btnCerrarSesion" value="Cerrar Sesión">
   			 </form>
   		 </li>
    </ul>
</div>
<%
} else {
%>
<div id="titulo">
    <h1 class="h1-layout">Inicio</h1>
</div>
<div id="menu">
    <ul class="ul-layout">
        <li><a href="Inicio.jsp">Login</a></li>
    </ul>
</div>
<%
}
%>
<div id="contenido">
    <!-- Espacio para mostrar el contenido particular de cada JSP -->
</div>
</body>
</html>

