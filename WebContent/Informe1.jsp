<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="Layout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seleccione Mes y Año</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css">
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>

<script>
	$(function() {
		$("#datepicker")
				.datepicker(
						{
							changeMonth : true,
							changeYear : true,
							showButtonPanel : true,
							dateFormat : 'mm/yy',
							onClose : function(dateText, inst) {
								var month = $(
										"#ui-datepicker-div .ui-datepicker-month :selected")
										.val();
								var year = $(
										"#ui-datepicker-div .ui-datepicker-year :selected")
										.val();
								var selectedDate = (parseInt(month) + 1) + '/'
										+ year;
								$(this).val(selectedDate);
							}
						});
	});

	$(document).ready(function() {
		$('#exampleTable').DataTable({
			"paging": true,
			"lengthMenu": [5, 10, 25, 50, 100],
			"pageLength": 10,
			"searching": true,
			"ordering": true
		});
	});
</script>
</head>
<body>
	<h1>Seleccione Mes y Año</h1>
	<form action="ServletCliente" method="get">
		<input type="text" id="datepicker" name="fecha"> <input
			type="submit" value="Buscar">
	</form>

	<h2>Tabla de Ejemplo</h2>
	<table id="exampleTable" class="display" border="1">
		<thead>
			<tr>
				<th>Tipo de Movimiento</th>
				<th>Importe Total</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Alta Cuenta</td>
				<td>100.00</td>
			</tr>
			<tr>
				<td>Pagar Préstamo</td>
				<td>75.50</td>
			</tr>
			<tr>
				<td>Adquirir Préstamo</td>
				<td>120.25</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
