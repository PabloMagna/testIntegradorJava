<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta" %>
<%
    // Obtener la cuenta de la sesión
    Cuenta cuentaPrestamo = (Cuenta) session.getAttribute("cuentaPrestamo");

    // Verificar si la cuenta es nula
    if (cuentaPrestamo == null) {
        // Realiza alguna acción o muestra un mensaje de error si es nula
        out.println("La cuenta de préstamo no está disponible.");
    } else {
        // La cuenta no es nula, puedes acceder a sus propiedades
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Solicitud de Préstamo</title>
</head>
<body>
    <h1>Solicitud de Préstamo</h1>
    <p>Número de Cuenta: <%= cuentaPrestamo.getNumero() %></p>
    <form action="ServletPrestamo" method="post">
        <label for="importe">Importe:</label>
        <input type="text" name="importe" required>
        <br>
        <label for="cuotas">Cuotas:</label>
        <input type="text" name="cuotas" required>
        <br>
        <input type="submit" name="btnPedirPrestamo" value="Solicitar Préstamo">
    </form>
</body>
</html>
<%
    }
%>
