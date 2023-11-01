<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Telefono"%>
<%@ page import="java.util.ArrayList"%>
<%@include file="Layout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Clientes</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script type="text/javascript" charset="utf8"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#clientesTable').DataTable();
	});
	<script type="text/javascript">
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#clientesTable').DataTable({
			"lengthMenu" : [ 5, 10, 25, 50, 100 ],
			"pageLength" : 5,
			"ordering" : true,
			"info" : true,
			"paging" : true
		});
	});
</script>
</head>
<body>
	<h2>Listado de Clientes</h2>

	<form method="get" action="ServletCliente">
		<label for="busqueda">Buscar:</label> <input type="text" id="busqueda"
			name="busqueda"
			value="<%=(request.getParameter("busqueda") != null) ? request.getParameter("busqueda") : ""%>">
		<input type="submit" name="btnBusqueda" value="Buscar"> <label
			for="edad">Edad:</label> <select name="operadorEdad">
			<option value="mayor"
				<%=(request.getParameter("operadorEdad") != null
					&& request.getParameter("operadorEdad").equals("mayor")) ? "selected" : ""%>>Mayor
				que:</option>
			<option value="menor"
				<%=(request.getParameter("operadorEdad") != null
					&& request.getParameter("operadorEdad").equals("menor")) ? "selected" : ""%>>Menor
				que:</option>
			<option value="igual"
				<%=(request.getParameter("operadorEdad") != null
					&& request.getParameter("operadorEdad").equals("igual")) ? "selected" : ""%>>Igual
				a:</option>
		</select> <input type="number" id="edad" name="edad"
			value="<%=request.getParameter("edad")%>"> <input
			type="submit" name="btnFiltrar" value="Filtrar">
	</form>

	<h2>Listado de Clientes</h2>
	<%
		ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("lista");
		ArrayList<Telefono> telefonosCliente = (ArrayList<Telefono>) request.getAttribute("listaTelefonos");

		if (lista != null && !lista.isEmpty()) {
	%>
	<table border="1" id="clientesTable" class="display">
		<thead>
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
				<th>Teléfonos</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Cliente cliente2 : lista) {
			%>
			<tr>
				<td><%=cliente2.getIdCliente()%></td>
				<td><%=cliente2.getUsuario()%></td>
				<td><%=cliente2.getContrasena()%></td>
				<td><%=(cliente2.getActivo() == 1) ? "Activo" : "Inactivo"%></td>
				<td><%=cliente2.getFechaCreacion()%></td>
				<td><%=(cliente2.getTipoCliente() == Cliente.TipoCliente.CLIENTE) ? "Cliente" : "Administrador"%></td>
				<td><%=cliente2.getDni()%></td>
				<td><%=cliente2.getCuil()%></td>
				<td><%=cliente2.getNombre()%></td>
				<td><%=cliente2.getApellido()%></td>
				<td><%=cliente2.getSexo()%></td>
				<td><%=cliente2.getNacionalidad()%></td>
				<td><%=cliente2.getFechaNacimiento()%></td>
				<td><%=cliente2.getDireccion()%></td>
				<td><%=cliente2.getCorreo()%></td>
				<td><%=cliente2.getLocalidad().getNombre()%></td>
				<td><%=cliente2.getProvincia().getNombre()%></td>
				<td><select name="telefonosCliente">
						<%
							if (telefonosCliente != null && !telefonosCliente.isEmpty()) {
										for (Telefono telefono : telefonosCliente) {
											if (telefono.getIdCliente() == cliente2.getIdCliente()) {
						%>
						<option value="<%=telefono.getNumero()%>"><%=telefono.getNumero()%></option>
						<%
							}
										}
									} else {
						%>
						<option value="No posee números">No posee números</option>
						<%
							}
						%>
				</select></td>

				<td><a
					href="ServletCliente?ModifId=<%=cliente2.getIdCliente()%>"> <i
						class="bi bi-pencil"></i> <!-- Icono de edición -->
				</a> <a href="ServletCuenta?AgregarId=<%=cliente2.getIdCliente()%>">
						<i class="bi bi-plus"></i> <!-- Icono de agregar o crear -->
				</a>

					<form method="post" action="ServletCliente">
						<input type="hidden" name="ElimId"
							value="<%=cliente2.getIdCliente()%>">
						<button type="submit" name="btnEliminar" class="btn-link">
							<i class="bi bi-trash"></i>
							<!-- Icono de eliminación -->
						</button>
					</form></td>


			</tr>
			<%
				}
			%>
		</tbody>
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

