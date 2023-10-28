<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.TipoCuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="Layout.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Formulario para Cargar una Cuenta</title>
</head>
<body>
    <h1>Formulario para Cargar una Cuenta</h1>
    <form action="ServletCuenta" method="post">
        <label for="idCliente">ID del Cliente:</label>
        <input type="text" name="idCliente" id="idCliente" value="<%= request.getAttribute("idCliente") %>" readonly><br>

        <label for="idTipoCuenta">Tipo de Cuenta:</label>
        <select name="idTipoCuenta" id="idTipoCuenta">
            <% ArrayList<TipoCuenta> tiposCuenta = (ArrayList<TipoCuenta>) request.getAttribute("tiposCuenta");
            	if(tiposCuenta != null){
               for (TipoCuenta tipoCuenta : tiposCuenta) { %>
                <option value="<%= tipoCuenta.getIdTipoCuenta() %>"><%= tipoCuenta.getDescripcion() %></option>
            <% } }%>
        </select><br>
        
        <input type="submit" value="Agregar Cuenta" name="btnAgregar">
    </form>
</body>
</html>
