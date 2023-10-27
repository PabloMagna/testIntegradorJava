<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listado de Cuentas del CLiente</title>
</head>
<body>
    <h1>Listado de Cuentas</h1>    
    <table border="1">
        <tr>
            <th>ID Cliente</th>
            <th>Número de Cuenta</th>
            <th>CBU</th>
            <th>Saldo</th>
            <th>Fecha</th>
            <th>Activo</th>
            <th>Tipo de Cuenta</th>
            <th>Acciones</th>
        </tr>

        <%
        ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");

        if (listaCuentas != null && !listaCuentas.isEmpty()) {
            for (Cuenta cuenta : listaCuentas) {
        %>
        <tr>
            <td><%= cuenta.getIdCliente() %></td>
            <td><%= cuenta.getNumero() %></td>
            <td><%= cuenta.getCBU() %></td>
            <td><%= cuenta.getSaldo() %></td>
            <td><%= cuenta.getFecha() %></td>
            <td><%= cuenta.getActivo() %></td>
            <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
            <td>
                <a href="ServletCuenta?historial=<%= cuenta.getNumero() %>">Historial</a>
            </td>
            <td>
                <a href="ServletCuenta?transferencia=<%= cuenta.getNumero() %>">Transferir</a>
            </td>
            <td>
                <a href="ServletPrestamo?pedirPrestamo=<%= cuenta.getNumero() %>">Pedir Préstamo</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="9">No se encontraron cuentas.</td>
        </tr>
        <%
        }
        %>
    </table>
</body>
</html>