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
            <th>Activo</th>
            <th>Fecha</th>
            <th>Tipo de Cliente</th>
            <th>Acciones</th>
        </tr>
        <%
        for (Cliente cliente : lista) {
        %>
        <tr>
            <td><%= cliente.getIdCliente() %></td>
            <td><%= cliente.getUsuario() %></td>
            <td><%= cliente.getActivo() %></td>
            <td><%= cliente.getFecha() %></td>
            <td><%= cliente.getTipoCliente() %></td>
             <td>
                <a href="ServletCliente?DetalleId=<%= cliente.getIdCliente() %>">Detalle</a>
            </td>
            <td>
                <a href="ServletCliente?ModifId=<%= cliente.getIdCliente() %>">Modificar</a>
      		</td>
      		<td>
  				  <form action="ServletCliente" method="post">
      				  <input type="hidden" name="ElimId" value="<%= cliente.getIdCliente() %>">
     			   <input type="submit" value="Eliminar" onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?')">
   			 </form>
			</td>
			 <td>
    			<a href="ServletCuenta?idCliente=<%= cliente.getIdCliente() %>">Ver Cuentas</a>
			</td>
             <td>
                <a href="ServletCuenta?AgregarId=<%= cliente.getIdCliente() %>">Agregar Cuenta</a>
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
