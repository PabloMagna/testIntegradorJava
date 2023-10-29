<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidad.Cliente"%>
<%@ page import="entidad.Provincia"%>
<%@ page import="entidad.Localidad"%>
<%@ page import="entidad.Telefono"%>
<%@ page import="java.util.ArrayList"%>
<%@include file="Layout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Agregar enlaces a jQuery, Bootstrap y Bootstrap Datepicker -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

<%
	Cliente clienteModificar = (Cliente) request.getAttribute("clienteModificar");
	ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
	ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("localidades");
	ArrayList<Telefono> listaTelefonos = (ArrayList<Telefono>)request.getAttribute("telefonos");
%>
<!-- Arreglos JavaScript de provincias y localidades -->
<script>
        var provinciasArray = [
            <%if (provincias != null) {
				for (Provincia prov : provincias) {%>
            { id: "<%=prov.getId()%>", nombre: "<%=prov.getNombre()%>" },
            <%}
			}%>
        ];

        var localidadesArray = [
            <%if (localidades != null) {
				for (Localidad loc : localidades) {%>
            { id: "<%=loc.getId()%>", provinciaId: "<%=loc.getIdProvincia()%>", nombre: "<%=loc.getNombre()%>" },
            <%}
			}%>
        ];
        //setea previo la fecha
        <%if (clienteModificar != null) {%>
    var fechaNacimientoCliente = "<%=clienteModificar.getFechaNacimiento()%>";

    if (fechaNacimientoCliente.trim() !== "") {
        // Convertir la fecha de MySQL a un formato válido de JavaScript (yyyy-mm-dd a mm/dd/yyyy)
        var fechaJavascript = fechaNacimientoCliente.replace(/(\d{4})-(\d{2})-(\d{2})/, "$2/$3/$1");
        $("#fechaNacimiento").val(fechaJavascript);
    }
<%}%>
$(function() {
    $(".datepicker").datepicker({
        format: 'yyyy-mm-dd'
    });

    // Manejar el evento de cambio en el desplegable de provincias
    $("#provincia").change(function() {
        console.log("Cambio en el desplegable de provincias");
        var provinciaId = $(this).val();
        if (provinciaId !== "") {
            // Filtrar las localidades según la provincia seleccionada
            var localidadesFiltradas = localidadesArray.filter(function(loc) {
                return loc.provinciaId === provinciaId;
            });

            // Rellenar el desplegable de localidades con los datos filtrados
            var localidadDropdown = $("#localidad");
            localidadDropdown.empty();
            localidadDropdown.append($("<option>").attr("value", "").text("Seleccionar Localidad"));
            $.each(localidadesFiltradas, function(i, loc) {
                localidadDropdown.append($("<option>").attr("value", loc.id).text(loc.nombre));
            });
        } else {
            // Si no se selecciona una provincia, vaciar el desplegable de localidades
            $("#localidad").html("<option value=''>Seleccionar Localidad</option>");
        }
    });

    // Agrega el código para establecer la provincia y localidad previas a la modificación
    
    <%if (clienteModificar != null) {%>         
    var provinciaId = "<%=clienteModificar.getProvincia() != null ? clienteModificar.getProvincia().getId() : ""%>";
    var localidadId = "<%=clienteModificar.getLocalidad() != null ? clienteModificar.getLocalidad().getId() : ""%>";

    // Selecciona la provincia y llena el desplegable de localidades (si hay una provincia seleccionada)
    if (provinciaId !== "") {
        $("#provincia").val(provinciaId).change();

        if (localidadId !== "") {
            $("#localidad").val(localidadId);
        }
    }
    <%}%>
});
function agregarTelefono() {
    var nuevoTelefono = $("#nuevoTelefono").val();
    if (nuevoTelefono.trim() !== "") {
        var telefonosList = $("#telefonosList");
        var listItem = $("<li>");
        var input = $("<input>");
        input.attr("type", "text");
        input.attr("name", "telefonos");
        input.val(nuevoTelefono);
        var deleteButton = $("<button>");
        deleteButton.text("Eliminar");
        deleteButton.click(function() {
            eliminarTelefono(this);
        });
        listItem.append(input);
        listItem.append(deleteButton);
        telefonosList.append(listItem);
        $("#nuevoTelefono").val("");
    }
}

function eliminarTelefono(button) {
    $(button).parent().remove();
}

    </script>

<meta charset="UTF-8">
<title>Detalles del Cliente</title>
</head>
<body>
	<h2>Agregar Cliente</h2>
	<%
		boolean viewOnly = request.getAttribute("viewOnly") != null;
		Provincia provincia = clienteModificar != null ? clienteModificar.getProvincia() : null;
	%>
	<form action="ServletCliente" method="post">
		<input type="hidden" name="idCliente"
			value="<%=(clienteModificar != null) ? clienteModificar.getIdCliente() : 0%>">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="usuario" placeholder="Usuario" required
						value="<%=(clienteModificar != null) ? clienteModificar.getUsuario() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<input type="password" name="contrasena" placeholder="Contraseña"
						required
						value="<%=(clienteModificar != null) ? clienteModificar.getContrasena() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<input type="text" name="dni" placeholder="DNI" required
						value="<%=(clienteModificar != null) ? clienteModificar.getDni() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="cuil" placeholder="CUIL" required
						value="<%=(clienteModificar != null) ? clienteModificar.getCuil() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<input type="text" name="nombre" placeholder="Nombre" required
						value="<%=(clienteModificar != null) ? clienteModificar.getNombre() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<input type="text" name="apellido" placeholder="Apellido" required
						value="<%=(clienteModificar != null) ? clienteModificar.getApellido() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<label>Sexo: </label> <select name="sexo" required
						<%=viewOnly ? "disabled" : ""%>>
						<option value="0"
							<%=((clienteModificar != null && clienteModificar.getSexo().ordinal() == 0) ? "selected" : "")%>>Varón</option>
						<option value="1"
							<%=((clienteModificar != null && clienteModificar.getSexo().ordinal() == 1) ? "selected" : "")%>>Mujer</option>
						<option value="2"
							<%=((clienteModificar != null && clienteModificar.getSexo().ordinal() == 2) ? "selected" : "")%>>Indefinido</option>
					</select>
				</div>
				<div class="col-md-4">
					<input type="text" name="nacionalidad" placeholder="Nacionalidad"
						required
						value="<%=(clienteModificar != null) ? clienteModificar.getNacionalidad() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<input type="text" name="fechaNacimiento" class="datepicker"
						placeholder="Fecha de Nacimiento" required id="fechaNacimiento">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="direccion" placeholder="Dirección"
						required
						value="<%=(clienteModificar != null) ? clienteModificar.getDireccion() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<!-- Desplegable de provincias -->
					<select name="provincia" id="provincia" required
						<%=viewOnly ? "disabled" : ""%>>
						<option value="">Seleccionar Provincia</option>
						<script>
                            // Llenar el desplegable de provincias desde el array de JavaScript
                            for (var i = 0; i < provinciasArray.length; i++) {
                                var provincia = provinciasArray[i];
                                document.write('<option value="' + provincia.id + '">' + provincia.nombre + '</option>');
                            }
                        </script>
					</select>
				</div>
				<div class="col-md-4">
					<!-- Desplegable de localidades -->
					<select name="localidad" id="localidad" required
						<%=viewOnly ? "disabled" : ""%>>
						<option value="">Seleccionar Localidad</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="correo" placeholder="Correo" required
						value="<%=(clienteModificar != null) ? clienteModificar.getCorreo() : ""%>"
						<%=viewOnly ? "readonly" : ""%>>
				</div>
				<div class="col-md-4">
					<div class="telefonos-container">
						<ul id="telefonosList">
							<!-- Aquí se mostrarán los teléfonos -->
							<%
								if (listaTelefonos != null) {
									for (Telefono telefono : listaTelefonos) {
							%>
							<li><input type="text" name="telefonos"
								value="<%=telefono.getNumero()%>">
								<button onclick="eliminarTelefono(this)">Eliminar</button></li>
							<%
								}
								}
							%>
						</ul>
						<input type="text" id="nuevoTelefono" placeholder="Nuevo Teléfono">
						<button onclick="agregarTelefono()">Agregar Teléfono</button>
					</div>

					<div class="col-md-4">
						<input type="submit"
							name="btn<%=(clienteModificar != null) ? "Modificar" : "Guardar"%>"
							value="<%=(clienteModificar != null) ? "Modificar" : "Guardar"%>" />
					</div>
				</div>
			</div>

			<a href="ServletCliente?lista=1">Volver al Listado</a>
	</form>
</body>
</html>
