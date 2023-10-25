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
        String consulta = "SELECT idTelefono, numero, activo FROM TELEFONOS WHERE idCliente = ? and activo = 1";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Telefono telefono = new Telefono();
                    telefono.setIdTelefono(resultSet.getInt("idTelefono"));
                    telefono.setIdCliente(idCliente); // Establece el idCliente al valor proporcionado como argumento.
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
    public int AgregarTelefono(int idCliente, String numero) {
        String consulta = "INSERT INTO TELEFONOS (idCliente, numero, activo) VALUES (?, ?, 1)";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idCliente);
            statement.setString(2, numero);

            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas;
        } catch (SQLException e) {
            System.err.println("Error al agregar un teléfono: " + e.getMessage());
        }

        return 0;
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
