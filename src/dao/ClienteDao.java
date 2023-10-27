package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import entidad.Cliente;
import entidad.Cliente.Sexo;
import entidad.Cliente.TipoCliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Telefono;
import interfazDao.IClienteDao;

public class ClienteDao implements IClienteDao {
    private Connection conexion;

    public ClienteDao() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public Cliente Login(String usuario, String contrasena) {
        // Implementación del método Login
    	String consulta = "SELECT c.*, l.ID AS localidadID, l.Nombre AS localidadNombre, p.ID AS provinciaID, p.Nombre AS provinciaNombre, t.idTelefono, t.numero, t.activo " +
    		    "FROM CLIENTE c " +
    		    "JOIN LOCALIDADES l ON c.idLocalidad = l.ID " +
    		    "JOIN PROVINCIAS p ON c.idProvincia = p.ID " +
    		    "LEFT JOIN TELEFONOS t ON c.idCliente = t.idCliente " +
    		    "WHERE c.usuario = ? AND c.contraseña = ? AND c.activo = 1";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, usuario);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                Cliente cliente = null;
                while (resultSet.next()) {
                    if (cliente == null) {
                        cliente = new Cliente();
                        cliente.setIdCliente(resultSet.getInt("idCliente"));
                        cliente.setUsuario(resultSet.getString("usuario"));
                        cliente.setContrasena(resultSet.getString("contraseña"));
                        cliente.setActivo(resultSet.getInt("activo"));
                        cliente.setFechaCreacion(resultSet.getDate("fechaCreacion").toLocalDate());
                        cliente.setTipoCliente(resultSet.getInt("idTipo"));
                        cliente.setDni(resultSet.getInt("dni"));
                        cliente.setCuil(resultSet.getString("cuil"));
                        cliente.setNombre(resultSet.getString("nombre"));
                        cliente.setApellido(resultSet.getString("apellido"));
                        cliente.setSexo(resultSet.getInt("sexo"));
                        cliente.setNacionalidad(resultSet.getString("nacionalidad"));
                        cliente.setFechaNacimiento(resultSet.getDate("fechaNacimiento").toLocalDate());
                        cliente.setDireccion(resultSet.getString("direccion"));
                        cliente.setCorreo(resultSet.getString("correo"));
                        cliente.setLocalidad(new Localidad(resultSet.getInt("localidadID"),resultSet.getInt("provinciaID"), resultSet.getString("localidadNombre")));
                        cliente.setProvincia(new Provincia(resultSet.getInt("provinciaID"), resultSet.getString("provinciaNombre")));
                        cliente.setTelefonos(new ArrayList<>());
                    }
                    if (resultSet.getInt("t.idTelefono") > 0) {
                        Telefono telefono = new Telefono();
                        telefono.setIdTelefono(resultSet.getInt("t.idTelefono"));
                        telefono.setNumero(resultSet.getString("t.numero"));
                        telefono.setActivo(resultSet.getInt("t.activo"));
                        cliente.getTelefonos().add(telefono);
                    }
                }
                return cliente;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta de login: " + e.getMessage());
        }

        return null;
    }

    @Override
    public int Agregar(Cliente cliente) {
        // Implementación del método AgregarCliente
        String consulta = "INSERT INTO CLIENTE (usuario, contraseña, activo, fechaCreacion, idTipo, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, idLocalidad, idProvincia, correo) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cliente.getUsuario());
            statement.setString(2, cliente.getContrasena());
            statement.setInt(3, cliente.getActivo());
            // Asignar la fecha actual directamente
            statement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(5, cliente.getTipoCliente().ordinal());
            statement.setInt(6, cliente.getDni());
            statement.setString(7, cliente.getCuil());
            statement.setString(8, cliente.getNombre());
            statement.setString(9, cliente.getApellido());
            statement.setInt(10, cliente.getSexo().ordinal());
            statement.setString(11, cliente.getNacionalidad());
            statement.setDate(12, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            statement.setString(13, cliente.getDireccion());
            statement.setInt(14, cliente.getLocalidad().getId());
            statement.setInt(15, cliente.getProvincia().getId());
            statement.setString(16, cliente.getCorreo());

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar un cliente: " + e.getMessage());
        }

        return 0;
    }



    @Override
    public int ModificarCliente(Cliente cliente) {
        // Implementación del método ModificarCliente
        String consulta = "UPDATE CLIENTE " +
                "SET usuario = ?, contraseña = ?, activo = ?, fechaCreacion = ?, idTipo = ?, dni = ?, cuil = ?, nombre = ?, apellido = ?, " +
                "sexo = ?, nacionalidad = ?, fechaNacimiento = ?, direccion = ?, idLocalidad = ?, idProvincia = ?, correo = ? " +
                "WHERE idCliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, cliente.getUsuario());
            statement.setString(2, cliente.getContrasena());
            statement.setInt(3, cliente.getActivo());
            statement.setDate(4, java.sql.Date.valueOf(cliente.getFechaCreacion()));
            statement.setInt(5, cliente.getTipoCliente().ordinal());
            statement.setInt(6, cliente.getDni());
            statement.setString(7, cliente.getCuil());
            statement.setString(8, cliente.getNombre());
            statement.setString(9, cliente.getApellido());
            statement.setInt(10, cliente.getSexo().ordinal());
            statement.setString(11, cliente.getNacionalidad());
            statement.setDate(12, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            statement.setString(13, cliente.getDireccion());
            statement.setInt(14, cliente.getLocalidad().getId());
            statement.setInt(15, cliente.getProvincia().getId());
            statement.setString(16, cliente.getCorreo());
            statement.setInt(17, cliente.getIdCliente());

            int filasActualizadas = statement.executeUpdate();

            return filasActualizadas;
        } catch (SQLException e) {
            System.err.println("Error al modificar cliente: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int EliminarCliente(int idCliente) {
        // Implementación del método EliminarCliente
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

    @Override
    public ArrayList<Cliente> ListarClientesActivos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String consultaClientes = "SELECT c.*, l.ID AS localidadID, l.Nombre AS localidadNombre, p.ID AS provinciaID, p.Nombre AS provinciaNombre " +
                "FROM CLIENTE c " +
                "JOIN LOCALIDADES l ON c.idLocalidad = l.ID " +
                "JOIN PROVINCIAS p ON c.idProvincia = p.ID " +
                "WHERE c.activo = 1";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(consultaClientes)) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setUsuario(resultSet.getString("usuario"));
                cliente.setContrasena(resultSet.getString("contraseña"));
                cliente.setActivo(resultSet.getInt("activo"));
                cliente.setFechaCreacion(resultSet.getDate("fechaCreacion").toLocalDate());
                cliente.setTipoCliente(resultSet.getInt("idTipo"));
                cliente.setDni(resultSet.getInt("dni"));
                cliente.setCuil(resultSet.getString("cuil"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setSexo(resultSet.getInt("sexo"));
                cliente.setNacionalidad(resultSet.getString("nacionalidad"));
                cliente.setFechaNacimiento(resultSet.getDate("fechaNacimiento").toLocalDate());
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setLocalidad(new Localidad(resultSet.getInt("localidadID"),resultSet.getInt("provinciaID"), resultSet.getString("localidadNombre")));
                cliente.setProvincia(new Provincia(resultSet.getInt("provinciaID"), resultSet.getString("provinciaNombre")));

                // Consulta para obtener los teléfonos del cliente
                String consultaTelefonos = "SELECT idTelefono, numero, activo FROM TELEFONOS WHERE idCliente = ? and activo = 1";
                try (PreparedStatement telefonoStatement = conexion.prepareStatement(consultaTelefonos)) {
                    telefonoStatement.setInt(1, cliente.getIdCliente());
                    try (ResultSet telefonoResultSet = telefonoStatement.executeQuery()) {
                        cliente.setTelefonos(new ArrayList<>());
                        while (telefonoResultSet.next()) {
                            Telefono telefono = new Telefono();
                            telefono.setIdTelefono(telefonoResultSet.getInt("idTelefono"));
                            telefono.setNumero(telefonoResultSet.getString("numero"));
                            telefono.setActivo(telefonoResultSet.getInt("activo"));
                            cliente.getTelefonos().add(telefono);
                        }
                    }
                }
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes activos: " + e.getMessage());
        }
        return clientes;
    }


    @Override
    public Cliente ObtenerPorIdCliente(int idCliente) {
        String consulta = "SELECT c.*, l.ID AS localidadID, l.Nombre AS localidadNombre, p.ID AS provinciaID, p.Nombre AS provinciaNombre, t.idTelefono, t.numero " +
                "FROM CLIENTE c " +
                "JOIN LOCALIDADES l ON c.idLocalidad = l.ID " +
                "JOIN PROVINCIAS p ON c.idProvincia = p.ID " +
                "LEFT JOIN TELEFONOS t ON c.idCliente = t.idCliente " +
                "WHERE c.idCliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                Cliente cliente = null;
                while (resultSet.next()) {
                    if (cliente == null) {
                        cliente = new Cliente();
                        cliente.setIdCliente(resultSet.getInt("idCliente"));
                        cliente.setUsuario(resultSet.getString("usuario"));
                        cliente.setContrasena(resultSet.getString("contraseña"));
                        cliente.setActivo(resultSet.getInt("activo"));
                        cliente.setFechaCreacion(resultSet.getDate("fechaCreacion").toLocalDate());
                        cliente.setTipoCliente(resultSet.getInt("idTipo"));
                        cliente.setDni(resultSet.getInt("dni"));
                        cliente.setCuil(resultSet.getString("cuil"));
                        cliente.setNombre(resultSet.getString("nombre"));
                        cliente.setApellido(resultSet.getString("apellido"));
                        cliente.setSexo(resultSet.getInt("sexo"));
                        cliente.setNacionalidad(resultSet.getString("nacionalidad"));
                        cliente.setFechaNacimiento(resultSet.getDate("fechaNacimiento").toLocalDate());
                        cliente.setDireccion(resultSet.getString("direccion"));
                        cliente.setCorreo(resultSet.getString("correo"));
                        cliente.setLocalidad(new Localidad(resultSet.getInt("localidadID"),resultSet.getInt("provinciaID"), resultSet.getString("localidadNombre")));
                        cliente.setProvincia(new Provincia(resultSet.getInt("provinciaID"), resultSet.getString("provinciaNombre")));
                        cliente.setTelefonos(new ArrayList<>());
                    }
                    if (resultSet.getInt("t.idTelefono") > 0) {
                        Telefono telefono = new Telefono();
                        telefono.setIdTelefono(resultSet.getInt("t.idTelefono"));
                        telefono.setNumero(resultSet.getString("t.numero"));
                        cliente.getTelefonos().add(telefono);
                    }
                }
                return cliente;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }

        return null;
    }


}
