<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Formulario para Modificar una Cuenta</title>
</head>
<body>
	<% 
    Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
    if (cuenta != null) {
%>
    <h1>Formulario para Modificar una Cuenta</h1>
    <form action="ServletCuenta" method="post">
       
        <label for="idCliente">ID del Cliente:</label>
        <input type="text" name="idCliente" id="idCliente" value="<%= cuenta.getIdCliente() %>" readonly><br>

        <label for="numero">Número de Cuenta:</label>
        <input type="text" name="numero" id="numero" value="<%= cuenta.getNumero() %>" readonly><br>

        <label for="CBU">CBU:</label>
        <input type="text" name="CBU" id="CBU" value="<%= cuenta.getCBU() %>" readonly><br>

        <label for="idTipoCuenta">Tipo de Cuenta:</label>
        <select name="idTipoCuenta" id="idTipoCuenta">
            <%
                ArrayList<TipoCuenta> tiposCuenta = (ArrayList<TipoCuenta>) request.getAttribute("tiposCuenta");
                int idTipoCuentaSeleccionado = cuenta.getTipoCuenta().getIdTipoCuenta();

                for (TipoCuenta tipoCuenta : tiposCuenta) {
                    // Comprobar si este tipo de cuenta es el seleccionado
                    String selected = (tipoCuenta.getIdTipoCuenta() == idTipoCuentaSeleccionado) ? "selected" : "";
            %>
            <option value="<%= tipoCuenta.getIdTipoCuenta() %>" <%= selected %>><%= tipoCuenta.getDescripcion() %></option>
            <%
                }
            %>
        </select><br>
        <label for="saldo">Saldo:</label>
        <input type="text" name="saldo" id="saldo" value="<%= cuenta.getSaldo() %>"><br>

        <input type="submit" value="Modificar Cuenta" name="btnModificar">
    </form>
<%
    } else {
%>
    <p>La cuenta no se pudo cargar correctamente.</p>
<%
    }
%>

    </form>
</body>
</html>
