package negocio;

import entidad.PruebaFecha;
import utilidades.ConversorFecha;
import dao.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PruebaFechaNegocio {
    public boolean Agregar(PruebaFecha pb) {
        // Establecer la conexi�n a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        if (conexion == null) {
            // Manejo de error: No se pudo establecer la conexi�n
            return false;
        }

        // Sentencia SQL para agregar un registro a la tabla pruebafechas
        String sql = "INSERT INTO pruebafechas (id, fecha) VALUES (?, ?)";

        try {
            // Crear una instancia de PreparedStatement
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            // Establecer los valores de los par�metros
            preparedStatement.setInt(1, pb.getId());

            // Realizar la conversi�n de formato de "dd/mm/yy" a "yyyy-mm-dd"
            String fechaEnFormatoEntrada = pb.getFecha(); // Suponiendo que tienes un m�todo para obtener la fecha como cadena
            Date fechaConvertida = ConversorFecha.convertirCadenaAFecha(fechaEnFormatoEntrada, "dd/mm/y");
            
            // Establecer la fecha convertida
            preparedStatement.setDate(2, new java.sql.Date(fechaConvertida.getTime()));

            // Ejecutar la sentencia SQL
            int filasAfectadas = preparedStatement.executeUpdate();

            // Verificar si se insert� correctamente
            if (filasAfectadas > 0) {
                // �xito: El registro se insert� correctamente
                return true;
            } else {
                // Manejo de error: No se pudo insertar el registro
                return false;
            }
        } catch (SQLException | ParseException e) {
            System.err.println("Error al agregar el registro: " + e.getMessage());
            return false;
        } finally {
            // Cerrar la conexi�n
            Conexion.cerrarConexion(conexion);
        }
    }
}

