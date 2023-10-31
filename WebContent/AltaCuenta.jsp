<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formulario para Cargar una Cuenta</title>
    <script>
        // Función para mostrar una ventana emergente de éxito y luego redirigir
        function mostrarMensajeExito() {
            alert("La operación se realizó con éxito");
            window.location.href = "Inicio.jsp"; // Redirige a la página deseada
        }

        // Función para mostrar una ventana emergente de advertencia
        function mostrarAdvertencia() {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'El cliente ya tiene 3 cuentas activas, que es el máximo permitido.'
            });
        }
        // Espera a que el documento esté listo
        document.addEventListener("DOMContentLoaded", function () {
            var advertencia = '<%= (String) request.getAttribute("advertencia") %>';
            if (advertencia != null) {
                mostrarAdvertencia();
            }
        });
    </script>
</head>
<body>
    <h1>Formulario para Cargar una Cuenta</h1>
    
    <% String advertencia = (String) request.getAttribute("advertencia");
       if (advertencia != null) { %>
    <script>
        mostrarAdvertencia();
    </script>
    <% } %>

    <form action="ServletCuenta" method="post">
        <label for="idCliente">ID del Cliente:</label>
        <input type="text" name="idCliente" id="idCliente" value="<%= request.getAttribute("idCliente") %>" readonly><br>

        <label for="idTipoCuenta">Tipo de Cuenta:</label>
        <select name="idTipoCuenta" id="idTipoCuenta">
            <%
            ArrayList<TipoCuenta> tiposCuenta = (ArrayList<TipoCuenta>) request.getAttribute("tiposCuenta");
            if (tiposCuenta != null) {
                for (TipoCuenta tipoCuenta : tiposCuenta) {
            %>
            <option value="<%= tipoCuenta.getIdTipoCuenta() %>"><%= tipoCuenta.getDescripcion() %></option>
            <%
                }
            }
            %>
        </select><br>

        <input type="submit" value="Agregar Cuenta" name="btnAgregar">
    </form>
</body>
</html>
