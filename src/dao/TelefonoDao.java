package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Telefono;
import interfazDao.ITelefonoDao;

public class TelefonoDao implements ITelefonoDao {
    private Connection conexion;

    public TelefonoDao() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public ArrayList<Telefono> ListarTelefonoPorIdCliente(int idCliente) {
        ArrayList<Telefono> telefonos = new ArrayList<>();
        String consulta = "SELECT idTelefono, idCliente, numero, activo FROM TELEFONOS WHERE idCliente = ? and activo = 1";
        
        if (idCliente == 0) {
            // Si idCliente es 0, listar todos los teléfonos activos sin filtrar por idCliente
            consulta = "SELECT idTelefono, idCliente, numero, activo FROM TELEFONOS WHERE activo = 1";
        }

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            if (idCliente > 0) {
                statement.setInt(1, idCliente);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Telefono telefono = new Telefono();
                    telefono.setIdTelefono(resultSet.getInt("idTelefono"));
                    telefono.setIdCliente(resultSet.getInt("idCliente")); // Completa con el nombre de la columna
                    telefono.setNumero(resultSet.getString("numero"));
                    telefono.setActivo(resultSet.getInt("activo"));
                    telefonos.add(telefono);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar teléfonos por ID de cliente: " + e.getMessage());
        }

        return telefonos;
    }


    @Override
    public int AgregarTelefonos(int idCliente, String[] numeros) {
        // Primero, marcar como inactivos los teléfonos existentes para el cliente
        String consultaDesactivar = "UPDATE TELEFONOS SET activo = 0 WHERE idCliente = ?";
        try (PreparedStatement statementDesactivar = conexion.prepareStatement(consultaDesactivar)) {
            statementDesactivar.setInt(1, idCliente);
            statementDesactivar.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al marcar como inactivos los teléfonos existentes: " + e.getMessage());
            return 0; // Otra opción es lanzar una excepción personalizada en lugar de retornar 0
        }

        // Si el array de números no es nulo y contiene al menos un número, agregar los nuevos teléfonos
        int filasInsertadas = 0;
        if (numeros != null && numeros.length > 0) {
            String consultaAgregar = "INSERT INTO TELEFONOS (idCliente, numero, activo) VALUES (?, ?, 1)";
            try (PreparedStatement statementAgregar = conexion.prepareStatement(consultaAgregar)) {
                for (String numero : numeros) {
                    statementAgregar.setInt(1, idCliente);
                    statementAgregar.setString(2, numero);
                    filasInsertadas += statementAgregar.executeUpdate();
                }
            } catch (SQLException e) {
                System.err.println("Error al agregar teléfonos nuevos: " + e.getMessage());
            }
        }

        return filasInsertadas;
    }

    @Override
    public int ModificarTelefono(int idTelefono, String nuevoNumero) {
        String consulta = "UPDATE TELEFONOS SET numero = ? WHERE idTelefono = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, nuevoNumero);
            statement.setInt(2, idTelefono);

            int filasActualizadas = statement.executeUpdate();

            return filasActualizadas;
        } catch (SQLException e) {
            System.err.println("Error al modificar un teléfono: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int EliminarTeléfono(int idTelefono) {
        String consulta = "UPDATE TELEFONOS SET activo = 0 WHERE idTelefono = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idTelefono);

            int filasActualizadas = statement.executeUpdate();

            return filasActualizadas;
        } catch (SQLException e) {
            System.err.println("Error al eliminar un teléfono: " + e.getMessage());
        }

        return 0;
    }

}
