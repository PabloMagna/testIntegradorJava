<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
</head>
<body>
    <h2>Listado de Clientes</h2>
    <%
    ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("lista");
    if (lista != null && !lista.isEmpty()) {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Contraseña</th>
            <th>Activo</th>
            <th>Fecha de Creación</th>
            <th>Tipo de Cliente</th>
            <th>DNI</th>
            <th>CUIL</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Sexo</th>
            <th>Nacionalidad</th>
            <th>Fecha de Nacimiento</th>
            <th>Dirección</th>
            <th>Correo</th>
            <th>Localidad</th>
            <th>Provincia</th>
             <th>Acciones</th>
        </tr>
        <%
        for (Cliente cliente : lista) {
        %>
        <tr>
            <td><%= cliente.getIdCliente() %></td>
            <td><%= cliente.getUsuario() %></td>
            <td><%= cliente.getContrasena() %></td>
            <td><%= (cliente.getActivo() == 1) ? "Activo" : "Inactivo" %></td>
            <td><%= cliente.getFechaCreacion() %></td>
            <td><%= (cliente.getTipoCliente() == Cliente.TipoCliente.CLIENTE) ? "Cliente" : "Administrador" %></td>
            <td><%= cliente.getDni() %></td>
            <td><%= cliente.getCuil() %></td>
            <td><%= cliente.getNombre() %></td>
            <td><%= cliente.getApellido() %></td>
            <td><%= cliente.getSexo() %></td>
            <td><%= cliente.getNacionalidad() %></td>
            <td><%= cliente.getFechaNacimiento() %></td>
            <td><%= cliente.getDireccion() %></td>
            <td><%= cliente.getCorreo() %></td>
            <td><%= cliente.getLocalidad().getNombre() %></td>
            <td><%= cliente.getProvincia().getNombre() %></td>
            <td>
                <a href="ServletCliente?ModifId=<%= cliente.getIdCliente() %>">Modificar</a>
            </td>
            <td>
                <a href="ServletCuenta?AgregarId=<%= cliente.getIdCliente() %>">Crear Cuenta</a>
            </td>
        </tr>
        <%
        }
        %>
    </table>
    <%
    } else {
    %>
    <p>No hay clientes para mostrar.</p>
    <%
    }
    %>
</body>
</html>

