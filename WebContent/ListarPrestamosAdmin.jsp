<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Préstamos</title>
</head>
<body>
    <h1>Lista de Préstamos</h1>
    
    <% 
        ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamo");
        if (listaPrestamos != null) {
    %>
    <table border="1">
        <tr>
            <th>ID Prestamo</th>
            <th>Número de Cuenta</th>
            <th>ID Cliente</th>
            <th>Importe Pedido</th>
            <th>Importe por Mes</th>
            <th>Cuotas</th>
            <th>Fecha Pedido</th>
            <th>Estado</th>
            <th>Aprobar</th>
        </tr>
        
        <% 
            for (Prestamo prestamo : listaPrestamos) {
        %>
            <tr>
                <td><%= prestamo.getIdPrestamo() %></td>
                <td><%= prestamo.getNumeroCuenta() %></td>
                <td><%= prestamo.getIdCliente() %></td>
                <td><%= prestamo.getImportePedido() %></td>
                <td><%= prestamo.getImportePorMes() %></td>
                <td><%= prestamo.getCuotas() %></td>
                <td><%= prestamo.getFechaPedido() %></td>
                <td><%= prestamo.getEstado() %></td>
                <td>
                    <form action="ServletPrestamo" method="post">
                        <input type="hidden" name="idPrestamo" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                        <button type="submit" name="btnAprobar" value="<%= prestamo.getIdPrestamo() %>">Aprobar</button>
                        <button type="submit" name="btnRechazar" value="<%= prestamo.getIdPrestamo() %>">Rechazar</button>
                    </form>
                </td>
            </tr>
        <% 
            }
        %>
    </table>
    <br>
    <% 
        } else {
    %>
    <p>No hay préstamos para mostrar.</p>
    <% 
        }
    %>
</body>
</html>