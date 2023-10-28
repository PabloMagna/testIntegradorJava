<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="servlets.ServletCuenta" %>
<%@include file="Layout.jsp" %>

<%
    // Obtener la cuenta de origen de la sesión
    Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuentaOrigen");

    // Verificar si la cuenta de origen no es nula
    if (cuentaOrigen == null) {
        // Redirigir a una página de error o realizar alguna acción apropiada
        response.sendRedirect("error.jsp");
    }

    // Obtener un mensaje de error si existe en la solicitud
    String errorMensaje = (String) request.getAttribute("errorMensaje");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Transferencia</title>
</head>
<body>
    <h1>Realizar Transferencia</h1>

  	 <%-- Mostrar mensaje de error si existe --%>
	<div class="error-message">
    <% if (errorMensaje != null) { %>
        <span><%= errorMensaje %></span>
    <% } %>
	</div>

    <form action="ServletCuenta" method="post">
        <label for="importe">Importe:</label>
        <input type="text" name="importe" id="importe" required>
        <br>

        <label for="cbu">CBU (22 números):</label>
        <input type="text" name="cbu" id="cbu" pattern="[0-9]{22}" required>
        <br>

        <label for="origen">Cuenta de Origen:</label>
        <%= cuentaOrigen.getNumero() %>
        <input type="hidden" name="cuentaOrigen" value="<%= cuentaOrigen.getNumero() %>">
        <br>

        <input type="submit" name="btnTransferir" value="Realizar Transferencia">
    </form>
</body>
</html>
