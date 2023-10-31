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
	ArrayList<Telefono> listaTelefonos = (ArrayList<Telefono>) request.getAttribute("telefonos");
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

    // Verificar que el número de teléfono contenga solo dígitos y, si está presente, un solo signo "+" al comienzo
    var telefonoValido = /^[+]?\d+$/;
    if (!telefonoValido.test(nuevoTelefono)) {
        alert("El número de teléfono debe contener solo dígitos y, si está presente, un signo + al comienzo.");
        return;
    }

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

    // Mostrar un mensaje de éxito
    alert("Teléfono ingresado con éxito.");
}

function eliminarTelefono(button) {
    var telefono = $(button).prev().val(); // Obtener el valor del teléfono que se eliminará
    $(button).parent().remove();
    alert("Teléfono eliminado: " + telefono); // Mostrar una alerta pop-up con el mensaje
}
function validarCampos() {
    var elementos = document.getElementsByTagName("input"); // Obtén todos los elementos de entrada

    for (var i = 0; i < elementos.length; i++) {
        var elemento = elementos[i];

        // Verifica si el elemento es un campo de texto y no es el campo para agregar teléfonos
        if (elemento.type === "text" && elemento.id !== "nuevoTelefono") {
            if (elemento.value.trim() === "") {
                alert("El campo '" + elemento.placeholder + "' no puede estar vacío.");
                elemento.value = ""; // Limpia el campo si contiene solo espacios en blanco
                elemento.focus(); // Coloca el foco en el campo vacío
                return false; // Evita que se envíe el formulario
            }
        }
    }
    return true; // Permite enviar el formulario si todos los campos son válidos
}
//Verificar la presencia del parámetro "exito" en la URL y mostrar un mensaje de éxito si está presente
<%if (request.getParameter("exito") != null && request.getParameter("exito").equals("true")) {%>
alert("Cliente agregado exitosamente.");
// Restablecer los valores de los campos
document.getElementById("usuario").value = "";
document.getElementById("contrasena").value = "";
document.getElementById("repetirContraseña").value = "";
document.getElementById("dni").value = "";
document.getElementById("cuil").value = "";
document.getElementById("nombre").value = "";
document.getElementById("apellido").value = "";
document.getElementById("sexo").value = "0"; // Ajusta esto según tu estructura
document.getElementById("nacionalidad").value = "";
document.getElementById("fechaNacimiento").value = "";
document.getElementById("direccion").value = "";
document.getElementById("provincia").value = "";
document.getElementById("localidad").value = "";
document.getElementById("correo").value = "";
document.getElementById("telefonosList").innerHTML = ""; // Limpia la lista de teléfonos
document.getElementById("nuevoTelefono").value = ""; // Limpia el campo de nuevo teléfono
<%}%>

<%if (request.getParameter("exito") != null && request.getParameter("exito").equals("true")) {%>
$(function() {
    // Muestra el mensaje de éxito
    $("#mensajeExito").show();
});
<%}%>

</script>

<meta charset="UTF-8">
<title>Detalles del Cliente</title>
</head>
<body>
	<div id="mensajeExito" style="display: none;"
		class="alert alert-success">Cliente agregado con éxito.</div>
	<h2>Agregar Cliente</h2>
	<%
		Provincia provincia = clienteModificar != null ? clienteModificar.getProvincia() : null;
	%>
	<form action="ServletCliente" method="post"
		onsubmit="return validarCampos();">
		<input type="hidden" name="idCliente"
			value="<%=(clienteModificar != null) ? clienteModificar.getIdCliente() : 0%>">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="usuario" placeholder="Usuario" required
						value="<%=(clienteModificar != null) ? clienteModificar.getUsuario() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="password" name="contrasena" id="contraseña"
						placeholder="Contraseña" required
						value="<%=(clienteModificar != null) ? clienteModificar.getContrasena() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="password" name="repetirContrasena"
						id="repetirContraseña" placeholder="Contraseña" required
						value="<%=(clienteModificar != null) ? clienteModificar.getContrasena() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="text" name="dni" placeholder="DNI" required
						value="<%=(clienteModificar != null) ? clienteModificar.getDni() : ""%>">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="cuil" placeholder="CUIL" required
						value="<%=(clienteModificar != null) ? clienteModificar.getCuil() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="text" name="nombre" placeholder="Nombre" required
						value="<%=(clienteModificar != null) ? clienteModificar.getNombre() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="text" name="apellido" placeholder="Apellido" required
						value="<%=(clienteModificar != null) ? clienteModificar.getApellido() : ""%>">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<label>Sexo: </label> <select name="sexo" required>
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
						value="<%=(clienteModificar != null) ? clienteModificar.getNacionalidad() : ""%>">
				</div>
				<div class="col-md-4">
					<input type="text" name="fechaNacimiento" class="datepicker"
						placeholder="Fecha de Nacimiento" required id="fechaNacimiento"
						value="<%=(clienteModificar != null) ? request.getAttribute("fechaNacimiento") : ""%>">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="text" name="direccion" placeholder="Dirección"
						required
						value="<%=(clienteModificar != null) ? clienteModificar.getDireccion() : ""%>">
				</div>
				<div class="col-md-4">
					<!-- Desplegable de provincias -->
					<select name="provincia" id="provincia" required>
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
					<select name="localidad" id="localidad" required>
						<option value="">Seleccionar Localidad</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<input type="email" name="correo" placeholder="Correo" required
						value="<%=(clienteModificar != null) ? clienteModificar.getCorreo() : ""%>">
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
						<button type="button" id="agregarTelefonoButton"
							onclick="agregarTelefono()">Agregar Teléfono</button>
					</div>

					<div class="col-md-4">
						<input type="submit"
							name="btn<%=(clienteModificar != null) ? "Modificar" : "Guardar"%>"
							value="<%=(clienteModificar != null) ? "Modificar" : "Guardar"%>" />
					</div>
				</div>
			</div>
		</div>

		<a href="ServletCliente?lista=1">Volver al Listado</a>
	</form>
</body>
</html>
