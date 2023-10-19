package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidad.Cliente;
import interfazDao.IClienteDao;

public class ClienteDao implements IClienteDao {
	
	private Connection conexion;

    public ClienteDao() {
		conexion = Conexion.obtenerConexion();
	}
	@Override
    public Cliente Login(String usuario, String contrasena) {
        String consulta = "SELECT * FROM CLIENTE WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, usuario);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Si se encontró un cliente con las credenciales proporcionadas, crear y devolver un objeto Cliente
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("idCliente"));
                    cliente.setUsuario(resultSet.getString("usuario"));
                    cliente.setContrasena(resultSet.getString("contrasena"));
                    // Establecer otros campos del cliente según la estructura de tu tabla

                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta de login: " + e.getMessage());
        }

        // Si no se encuentra un cliente con las credenciales proporcionadas, devuelve null.
        return null;
    }
}
