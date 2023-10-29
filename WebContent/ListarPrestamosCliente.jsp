<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cuota" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.*" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Préstamos</title>
</head>
<body>
    <h1>Lista de Préstamos</h1>
    	<form method="get" action="ServletCliente"><%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
        <label for="busqueda">Buscar:</label>
        <input type="text" id="busqueda" name="busqueda">
        <input type="submit" name="btnBusqueda" value="Buscar">
        
        <label for="edad">Importe:</label> <%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
        <select name="operadorEdad">
            <option value="mayor">Mayor que:</option>
            <option value="menor">Menor que:</option>
            <option value="igual">Igual a:</option>
        </select>
        <input type="number" id="edad" name="edad"><%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
    </form>

    <form action="ServletPrestamo" method="post">
        <table border="1">
            <tr>
                <th>ID Préstamo</th>
                <th>Seleccionar Cuota</th>
                <th>Importe</th>
                <th>Seleccionar Cuenta</th> <!-- Agregado -->
                <th>Pagar</th>
            </tr>

            <%
                ArrayList<Cuota> listaCuotas = (ArrayList<Cuota>) request.getAttribute("listaCuotas");
                ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas"); // Agregado

                if (listaCuotas != null) {
                    // Crear un conjunto de ID de préstamo únicos
                    Set<Integer> idPrestamosUnicos = new HashSet<>();
                    for (Cuota cuota : listaCuotas) {
                        idPrestamosUnicos.add(cuota.getIdPrestamo());
                    }

                    for (Integer idPrestamo : idPrestamosUnicos) {
                        // Filtrar las cuotas relacionadas con este ID de préstamo
                        ArrayList<Cuota> cuotasDelPrestamo = new ArrayList<Cuota>();
                        for (Cuota cuota : listaCuotas) {
                            if (cuota.getIdPrestamo() == idPrestamo) {
                                cuotasDelPrestamo.add(cuota);
                            }
                        }
            %>
            <tr>
                <td><%= idPrestamo %></td>
                <td>
                    <select name="cuota">
                        <option value="">Seleccionar Cuota</option>
                        <%
                            for (Cuota cuota : cuotasDelPrestamo) {
                        %>
                        <option value="<%= cuota.getNumeroCuota() %>"><%= cuota.getNumeroCuota() %></option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td><input id="importe" name="importe" value=<%= cuotasDelPrestamo.get(0).getImporte() %> readonly></td>
                <td> <!-- Agregado -->
                    <select name="cuenta">
                        <option value="">Seleccionar Cuenta</option>
                        <%
                            for (Cuenta cuenta : listaCuentas) {
                        %>
                        <option value="<%= cuenta.getNumero() %>"><%= cuenta.getNumero() %></option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td>
                    <button type="submit" name="btnPagar" value="<%= idPrestamo %>">Pagar</button>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No hay préstamos para mostrar.</td>
            </tr>
            <%
                }
            %>
        </table>
    </form>
</body>
</html>
