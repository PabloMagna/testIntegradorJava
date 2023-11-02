<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<%@include file="Layout.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Incluye jQuery 3.6.0 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Incluye DataTables 1.13.6 -->
    <link rel="stylesheet"
        href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css">
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
    <link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listado de Cuentas</title>
</head>
<body>
    <h1>Listado de Cuentas</h1>
    <form method="get" action="ServletCuenta">
        <label for="busqueda">Buscar:</label> <input type="text" id="busqueda"
            name="busqueda"> <input type="submit" name="btnBusqueda"
            value="Buscar"> <label for="saldo">Saldo:</label>
        <select name="operadorSaldo">
            <option value="mayor">Mayor que:</option>
            <option value="menor">Menor que:</option>
            <option value="igual">Igual a:</option>
        </select> <input type="number" id="saldo" name="saldo">
    </form>

    <!-- Alerta de éxito -->
    <% if (request.getAttribute("exitoEliminacion") != null && (boolean) request.getAttribute("exitoEliminacion")) { %>
    <div class="alert alert-success">
        La cuenta se eliminó correctamente.
    </div>
    <% } %>

    <!-- Alerta de error -->
    <% if (request.getAttribute("exitoEliminacion") != null && !(boolean) request.getAttribute("exitoEliminacion")) { %>
    <div class="alert alert-danger">
        No se pudo eliminar la cuenta.
    </div>
    <% } %>

    <%
        ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
        if (listaCuentas != null && !listaCuentas.isEmpty()) {
    %>
    <table border="1" id="cuentasTable" class="display">
        <thead>
            <tr>
                <th>ID Cliente</th>
                <th>Número de Cuenta</th>
                <th>CBU</th>
                <th>Saldo</th>
                <th>Fecha</th>
                <th>Activo</th>
                <th>Tipo de Cuenta</th>
                <th>Borrar</th>
                <th>Modificar</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Cuenta cuenta : listaCuentas) {
            %>

            <tr>
                <td><%=cuenta.getIdCliente()%></td>
                <td><%=cuenta.getNumero()%></td>
                <td><%=cuenta.getCBU()%></td>
                <td><%=cuenta.getSaldo()%></td>
                <td><%=cuenta.getFecha()%></td>
                <td><%=cuenta.getActivo()%></td>
                <td><%=cuenta.getTipoCuenta().getDescripcion()%></td>

                <td>
                    <button class="btn btn-danger" data-toggle="tooltip"
                        data-placement="top" title="Eliminar"
                        onclick="confirmarEliminacion(<%=cuenta.getNumero()%>);">
                        <i class="bi bi-trash"></i> Eliminar
                    </button>
                </td>
                <td><a
                    href="ServletCuenta?ModificarNumero=<%=cuenta.getNumero()%>"
                    class="btn btn-primary" data-toggle="tooltip" data-placement="top"
                    title="Modificar"> <i class="bi bi-pencil"></i> Modificar
                </a></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        } else {
    %>
    <p>No se encontraron cuentas.</p>
    <%
        }
    %>

    <script>
        function confirmarEliminacion(idCuenta) {
            const confirmacion = confirm("¿Seguro que deseas eliminar esta cuenta?");
            if (confirmacion) {
                window.location.href = "ServletCuenta?EliminarNumeroCuenta=" + idCuenta;
            }
        }

        $(document).ready(function() {
            $('#cuentasTable').DataTable({
                "paging": true,
                "lengthMenu": [5, 10, 25, 50, 100],
                "pageLength": 10,
                "searching": true,
                "ordering": true
            });
        });
     // Obtén el mensaje de éxito o error del servidor y muestra una alerta
        <%
        if (request.getAttribute("exitoEliminacion") != null) {
            boolean exitoEliminacion = (boolean) request.getAttribute("exitoEliminacion");
            if (exitoEliminacion) {
        %>
        // Alerta de eliminación exitosa
        alert("La cuenta se eliminó correctamente.");
        <%
            } else {
        %>
        // Alerta de error en la eliminación
        alert("No se pudo eliminar la cuenta.");
        <%
            }
        }
        %>
    </script>
</body>
</html>
