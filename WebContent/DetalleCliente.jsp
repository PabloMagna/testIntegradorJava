<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Datos del Cliente</title>
</head>
<body>
    <h1>Datos del Cliente</h1>
    <%
    	HttpSession ses2 = request.getSession(false);
   		Cliente cliente2 = (Cliente) ses.getAttribute("cliente");
        if (cliente2 != null) {
    %>
    <table>
        <tr>
            <td><strong>Nombre:</strong></td>
            <td><%= cliente2.getNombre() %></td>
        </tr>
        <tr>
            <td><strong>Apellido:</strong></td>
            <td><%= cliente2.getApellido() %></td>
        </tr>
        <tr>
            <td><strong>DNI:</strong></td>
            <td><%= cliente2.getDni() %></td>
        </tr>
        <tr>
            <td><strong>CUIL:</strong></td>
            <td><%= cliente2.getCuil() %></td>
        </tr>
        <tr>
            <td><strong>Sexo:</strong></td>
            <td><%= (cliente2.getSexo() == entidad.Cliente.Sexo.VARON) ? "Varón" : (cliente.getSexo() == entidad.Cliente.Sexo.MUJER) ? "Mujer" : "Indefinido" %></td>
        </tr>
        <tr>
            <td><strong>Nacionalidad:</strong></td>
            <td><%= cliente2.getNacionalidad() %></td>
        </tr>
        <tr>
            <td><strong>Fecha de Nacimiento:</strong></td>
            <td><%= cliente2.getFechaNacimiento() %></td>
        </tr>
        <tr>
            <td><strong>Dirección:</strong></td>
            <td><%= cliente2.getDireccion() %></td>
        </tr>
        <tr>
            <td><strong>Correo:</strong></td>
            <td><%= cliente2.getCorreo() %></td>
        </tr>
        <tr>
            <td><strong>Localidad:</strong></td>
            <td><%= cliente2.getLocalidad().getNombre() %></td>
        </tr>
        <tr>
            <td><strong>Provincia:</strong></td>
            <td><%= cliente2.getProvincia().getNombre() %></td>
        </tr>
    </table>
    <%
        } else {
    %>
    <p>No se encontró un cliente en la sesión.</p>
    <%
        }
    %>
    <p><a href="Inicio.jsp">Volver al Inicio</a></p>
</body>
</html>
