<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="entidad.DatosCliente" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Agregar enlaces a jQuery, Bootstrap y Bootstrap Datepicker -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

    <!-- Inicializar el selector de fechas en elementos con la clase "datepicker" -->
    <script>
        $(function() {
            $(".datepicker").datepicker({
                format: 'yyyy-mm-dd'
            });
        });
    </script>

    <meta charset="UTF-8">
    <title>Detalles del Cliente</title>
</head>
<body>
    <h2>Detalles del Cliente</h2>
    <%
        Cliente clienteModificar = (Cliente) request.getAttribute("clienteModificar");
        DatosCliente datosCliente = (DatosCliente) request.getAttribute("datosCliente");
        boolean viewOnly = request.getAttribute("viewOnly") != null;
    %>
    <form action="ServletCliente" method="post">
        <input type="hidden" name="idCliente" value="<%= clienteModificar != null ? clienteModificar.getIdCliente() : "" %>">
        <input type="text" name="usuario" placeholder="Usuario" required value="<%= clienteModificar != null ? clienteModificar.getUsuario() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="password" name="contrasena" placeholder="Contraseña" required value="<%= clienteModificar != null ? clienteModificar.getContrasena() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="dni" placeholder="DNI" required value="<%= datosCliente != null ? datosCliente.getDni() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="cuil" placeholder="CUIL" required value="<%= datosCliente != null ? datosCliente.getCuil() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="nombre" placeholder="Nombre" required value="<%= datosCliente != null ? datosCliente.getNombre() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="apellido" placeholder="Apellido" required value="<%= datosCliente != null ? datosCliente.getApellido() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="sexo" placeholder="Sexo" required value="<%= datosCliente != null ? datosCliente.getSexo() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="nacionalidad" placeholder="Nacionalidad" required value="<%= datosCliente != null ? datosCliente.getNacionalidad() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="fechaNacimiento" class="datepicker" placeholder="Fecha de Nacimiento" required value="<%= datosCliente != null ? datosCliente.getFechaNacimiento() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="direccion" placeholder="Dirección" required value="<%= datosCliente != null ? datosCliente.getDireccion() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="localidad" placeholder="Localidad" required value="<%= datosCliente != null ? datosCliente.getLocalidad() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="provincia" placeholder="Provincia" required value="<%= datosCliente != null ? datosCliente.getProvincia() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="correo" placeholder="Correo" required value="<%= datosCliente != null ? datosCliente.getCorreo() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="text" name="telefono" placeholder="Teléfono" required value="<%= datosCliente != null ? datosCliente.getTelefono() : "" %>" <%= viewOnly ? "readonly" : "" %>>
        <input type="submit" name="<%= clienteModificar != null && !viewOnly ? "btnModificar" : "btnGuardar" %>" value="<%= clienteModificar != null && !viewOnly ? "Modificar" : "Guardar" %>" <%= viewOnly ? "style='display: none;'" : "" %>>
        <a href="ServletCliente?lista=1">Volver al Listado</a>
    </form>
</body>
</html>
