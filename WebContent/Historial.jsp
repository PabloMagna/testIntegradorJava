<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Movimiento" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Historial de Movimientos</title>
</head>
<body>
    <h1>Historial de Movimientos</h1>

    <% 
    // Recupera la lista de movimientos desde el atributo "movimientos"
    ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) request.getAttribute("movimientos");

    // Comprueba si la lista no es nula y no está vacía
    if (movimientos != null && !movimientos.isEmpty()) { 
    %>
    <table border="1">
        <tr>
            <th>ID Movimiento</th>
            <th>Detalle</th>
            <th>Importe</th>
            <th>Tipo de Movimiento</th>
            <th>Fecha</th>
        </tr>
        <% 
        // Itera a través de la lista de movimientos y muestra cada uno
        for (Movimiento movimiento : movimientos) {
        %>
        <tr>
            <td><%= movimiento.getIdMovimiento() %></td>
            <td><%= movimiento.getDetalle() %></td>
            <td><%= movimiento.getImporte() %></td>
            <td><%= movimiento.getIdTipoMovimiento().getDescripcion() %></td>
            <td><%= movimiento.getFecha() %></td>
        </tr>
        <%
        }
        %>
    </table>
    <%
    } else { // La lista de movimientos está vacía o nula
    %>
    <p>No hay movimientos disponibles.</p>
    <%
    }
    %>
</body>
</html>
