package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.Cliente.tipoCliente;
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
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("idCliente"));
                    cliente.setUsuario(resultSet.getString("usuario"));
                    cliente.setContrasena(resultSet.getString("contrasena"));
                    cliente.setActivo(resultSet.getInt("activo"));
                    cliente.setFecha(resultSet.getDate("fecha"));
                    cliente.setTipoCliente(resultSet.getInt("idTipo"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta de login: " + e.getMessage());
        }

        return null;
    }
	@Override
	public int Agregar(Cliente cliente) { //devuelve id también
	    String consulta = "INSERT INTO CLIENTE (usuario, contrasena, activo, fecha, idTipo) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
	        statement.setString(1, cliente.getUsuario());
	        statement.setString(2, cliente.getContrasena());
	        statement.setInt(3, 1); // Siempre activo
	        statement.setDate(4, new java.sql.Date(new java.util.Date().getTime())); // Fecha actual

	        // Establecer tipoCliente siempre como CLIENTE
	        statement.setInt(5, 0);

	        // Ejecutar la inserción
	        int filasInsertadas = statement.executeUpdate();

	        if (filasInsertadas > 0) {
	            // Recuperar el idCliente generado automáticamente
	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int idCliente = generatedKeys.getInt(1);

	                // Devuelve el idCliente generado
	                return idCliente;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al agregar un cliente: " + e.getMessage());
	    }

	    // En caso de error o inserción fallida
	    return 0;
	}
	@Override
	public ArrayList<Cliente> ListarClientesActivos() {
	    ArrayList<Cliente> clientes = new ArrayList<>();
	    String consulta = "SELECT * FROM CLIENTE WHERE activo = 1";
	    try (Statement statement = conexion.createStatement();
	         ResultSet resultSet = statement.executeQuery(consulta)) {
	        while (resultSet.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(resultSet.getInt("idCliente"));
	            cliente.setUsuario(resultSet.getString("usuario"));
	            cliente.setContrasena(resultSet.getString("contrasena"));
	            cliente.setActivo(resultSet.getInt("activo"));
	            cliente.setFecha(resultSet.getDate("fecha"));
	            cliente.setTipoCliente(resultSet.getInt("idTipo"));
	            clientes.add(cliente);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al listar clientes activos: " + e.getMessage());
	    }
	    return clientes;
	}
	public Cliente ObtenerPorIdCliente(int idCliente) {
	    String consulta = "SELECT * FROM CLIENTE WHERE idCliente = ?";
	    try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
	        statement.setInt(1, idCliente);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                Cliente cliente = new Cliente();
	                cliente.setIdCliente(resultSet.getInt("idCliente"));
	                cliente.setUsuario(resultSet.getString("usuario"));
	                cliente.setContrasena(resultSet.getString("contrasena"));
	                cliente.setActivo(resultSet.getInt("activo"));
	                cliente.setFecha(resultSet.getDate("fecha"));
	                cliente.setTipoCliente(resultSet.getInt("idTipo"));

	                return cliente;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener cliente por ID: " + e.getMessage());
	    }

	    return null;
	}
	@Override
	public int ModificarCliente(Cliente cliente) {
	    String consulta = "UPDATE CLIENTE SET usuario = ?, contrasena = ? WHERE idCliente = ?";
	    try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
	        statement.setString(1, cliente.getUsuario());
	        statement.setString(2, cliente.getContrasena());
	        statement.setInt(3, cliente.getIdCliente());

	        int filasActualizadas = statement.executeUpdate();

	        return filasActualizadas;
	    } catch (SQLException e) {
	        System.err.println("Error al modificar cliente: " + e.getMessage());
	    }

	    return 0;
	}
	@Override
	public int EliminarCliente(int idCliente) {
	    String consulta = "UPDATE CLIENTE SET activo = 0 WHERE idCliente = ?";
	    
	    try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
	        statement.setInt(1, idCliente);
	        int filasActualizadas = statement.executeUpdate();
	        
	        return filasActualizadas;
	    } catch (SQLException e) {
	        System.err.println("Error al realizar la baja lógica de un cliente: " + e.getMessage());
	    }

	    return 0;
	}


}
