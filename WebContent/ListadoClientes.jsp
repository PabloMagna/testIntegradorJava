<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
</head>
<body>
	<h2>Listado de Clientes</h2>
	<form method="get" action="ServletCliente">
    	<label for="busqueda">Buscar:</label>
    	<input type="text" id="busqueda" name="busqueda">
    	<input type="submit" name="btnBusqueda"value="Buscar">
	</form>
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
        for (Cliente cliente2 : lista) {
        %>
        <tr>
            <td><%= cliente2.getIdCliente() %></td>
            <td><%= cliente2.getUsuario() %></td>
            <td><%= cliente2.getContrasena() %></td>
            <td><%= (cliente2.getActivo() == 1) ? "Activo" : "Inactivo" %></td>
            <td><%= cliente2.getFechaCreacion() %></td>
            <td><%= (cliente2.getTipoCliente() == Cliente.TipoCliente.CLIENTE) ? "Cliente" : "Administrador" %></td>
            <td><%= cliente2.getDni() %></td>
            <td><%= cliente2.getCuil() %></td>
            <td><%= cliente2.getNombre() %></td>
            <td><%= cliente2.getApellido() %></td>
            <td><%= cliente2.getSexo() %></td>
            <td><%= cliente2.getNacionalidad() %></td>
            <td><%= cliente2.getFechaNacimiento() %></td>
            <td><%= cliente2.getDireccion() %></td>
            <td><%= cliente2.getCorreo() %></td>
            <td><%= cliente2.getLocalidad().getNombre() %></td>
            <td><%= cliente2.getProvincia().getNombre() %></td>
            <td>
                <a href="ServletCliente?ModifId=<%= cliente2.getIdCliente() %>">Modificar</a>
            </td>
            <td>
  			  <form method="post" action="ServletCliente">
       		 	<input type="hidden" name="ElimId" value="<%= cliente2.getIdCliente() %>">
        		<input type="submit" name="btnEliminar" value="Eliminar">
   			 </form>
			</td>
            <td>
                <a href="ServletCuenta?AgregarId=<%= cliente2.getIdCliente() %>">Crear Cuenta</a>
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

