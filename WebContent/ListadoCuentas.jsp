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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Cuentas</title>
<style>
.exito {
	color: green;
}

.error {
	color: red;
}

.popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	padding: 15px;
	background: #fff;
	border: 1px solid #ccc;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	z-index: 1000;
}
</style>
<script>
        function confirmarEliminacion(idCuenta) {
            const confirmacion = confirm("¿Seguro que deseas eliminar esta cuenta?");
            if (confirmacion) {
                window.location.href = "ServletCuenta?EliminarNumeroCuenta=" + idCuenta;
            }
        }
        
        $(document).ready(function() {
            $('#cuentasTable').DataTable({
                "paging": true,  // Habilita la paginación
                "lengthMenu": [5, 10, 25, 50, 100],  // Define las opciones de cantidad de filas por página
                "pageLength": 10,  // Establece la cantidad de filas por página predeterminada
                "searching": true,  // Habilita la búsqueda
                "ordering": true,   // Habilita el ordenamiento
                // Otras opciones de configuración según tus necesidades
            });
        });
    </script>
</head>
<body>
	<h1>Listado de Cuentas</h1>
	<form method="get" action="ServletCuenta">
		<%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
		<label for="busqueda">Buscar:</label> <input type="text" id="busqueda"
			name="busqueda"> <input type="submit" name="btnBusqueda"
			value="Buscar"> <label for="saldo">Saldo:</label>
		<%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
		<select name="operadorSaldo">
			<option value="mayor">Mayor que:</option>
			<option value="menor">Menor que:</option>
			<option value="igual">Igual a:</option>
		</select> <input type="number" id="saldo" name="saldo">
		<%--NADA TIENE FUNCIONALIDAD EN ESTE FORM, solo para presentar tp1 --%>
	</form>
	<%
		if (request.getAttribute("exitoEliminacion") != null) {
			boolean exitoEliminacion = (boolean) request.getAttribute("exitoEliminacion");
			if (exitoEliminacion) {
	%>
	<div class="exito">La cuenta se eliminó correctamente.</div>
	<%
		} else {
	%>
	<div class="error">No se pudo eliminar la cuenta.</div>
	<%
		}
		}
	%>

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

				<td><a href="javascript:void(0);"
					onclick="confirmarEliminacion(<%=cuenta.getNumero()%>);">Eliminar</a>
				</td>
				<td><a
					href="ServletCuenta?ModificarNumero=<%=cuenta.getNumero()%>">Modificar</a>
				</td>
			</tr>
				<%
					}
				%>
			
		</tbody>
	</table>
	<%
		} else {
	%>
	<p>	No se encontraron cuentas.</p>
		<%
		}
	%>
	
</body>
</html>

