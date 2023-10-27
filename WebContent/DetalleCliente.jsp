<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Datos del Cliente</title>
</head>
<body>
    <h1>Datos del Cliente</h1>
    <%
        // Obtiene el objeto cliente almacenado en la sesión
        entidad.Cliente cliente = (entidad.Cliente) session.getAttribute("cliente");

        if (cliente != null) {
    %>
    <table>
        <tr>
            <td><strong>Nombre:</strong></td>
            <td><%= cliente.getNombre() %></td>
        </tr>
        <tr>
            <td><strong>Apellido:</strong></td>
            <td><%= cliente.getApellido() %></td>
        </tr>
        <tr>
            <td><strong>DNI:</strong></td>
            <td><%= cliente.getDni() %></td>
        </tr>
        <tr>
            <td><strong>CUIL:</strong></td>
            <td><%= cliente.getCuil() %></td>
        </tr>
        <tr>
            <td><strong>Sexo:</strong></td>
            <td><%= (cliente.getSexo() == entidad.Cliente.Sexo.VARON) ? "Varón" : (cliente.getSexo() == entidad.Cliente.Sexo.MUJER) ? "Mujer" : "Indefinido" %></td>
        </tr>
        <tr>
            <td><strong>Nacionalidad:</strong></td>
            <td><%= cliente.getNacionalidad() %></td>
        </tr>
        <tr>
            <td><strong>Fecha de Nacimiento:</strong></td>
            <td><%= cliente.getFechaNacimiento() %></td>
        </tr>
        <tr>
            <td><strong>Dirección:</strong></td>
            <td><%= cliente.getDireccion() %></td>
        </tr>
        <tr>
            <td><strong>Correo:</strong></td>
            <td><%= cliente.getCorreo() %></td>
        </tr>
        <tr>
            <td><strong>Localidad:</strong></td>
            <td><%= cliente.getLocalidad().getNombre() %></td>
        </tr>
        <tr>
            <td><strong>Provincia:</strong></td>
            <td><%= cliente.getProvincia().getNombre() %></td>
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
