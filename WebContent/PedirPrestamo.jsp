<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidad.Cuenta" %>
<%@include file="Layout.jsp" %>
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
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">   
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Solicitud de Préstamo</title>
</head>
<body>
    <div class="container">
        <h1 class="mt-4">Solicitud de Préstamo</h1>
        <p class="mb-4">Número de Cuenta: <%= cuentaPrestamo.getNumero() %></p>

        <form action="ServletPrestamo" method="post">
            <div class="form-group">
                <label for="importe">Importe:</label>
                <input type="text" name="importe" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="cuotas">Cuotas:</label>
                <input type="text" name="cuotas" class="form-control" required>
            </div>
            <button type="submit" name="btnPedirPrestamo" class="btn btn-primary">Solicitar Préstamo</button>
        </form>
    </div>
</body>
</html>
<%
    }
%>
