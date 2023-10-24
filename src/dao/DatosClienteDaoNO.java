package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidad.DatosCliente;
import interfazDao.IDatosClienteDaoNO;

public class DatosClienteDaoNO implements IDatosClienteDaoNO {
    private Connection conexion;

    public DatosClienteDaoNO() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public int Agregar(DatosCliente idCliente) {
        String consulta = "INSERT INTO datoscliente (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo, telefono, idCliente) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idCliente.getDni());
            statement.setString(2, idCliente.getCuil());
            statement.setString(3, idCliente.getNombre());
            statement.setString(4, idCliente.getApellido());
            statement.setString(5, idCliente.getSexo());
            statement.setString(6, idCliente.getNacionalidad());

            // Conversión de java.util.Date a java.sql.Date
            java.util.Date fechaUtil = idCliente.getFechaNacimiento();
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            statement.setDate(7, fechaSql);

            statement.setString(8, idCliente.getDireccion());
            statement.setString(9, idCliente.getLocalidad());
            statement.setString(10, idCliente.getProvincia());
            statement.setString(11, idCliente.getCorreo());
            statement.setString(12, idCliente.getTelefono());
            statement.setInt(13, idCliente.getIdCliente());

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                return filasInsertadas;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar un cliente: " + e.getMessage());
        }
        return 0;
    }
    public DatosCliente BuscarPorIdCliente(int idCliente) {
        DatosCliente clienteEncontrado = null;
        String consulta = "SELECT dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo, telefono FROM datoscliente WHERE idCliente = ?";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, idCliente);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int dni = resultSet.getInt("dni");
                String cuil = resultSet.getString("cuil");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String sexo = resultSet.getString("sexo");
                String nacionalidad = resultSet.getString("nacionalidad");
                java.util.Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                String direccion = resultSet.getString("direccion");
                String localidad = resultSet.getString("localidad");
                String provincia = resultSet.getString("provincia");
                String correo = resultSet.getString("correo");
                String telefono = resultSet.getString("telefono");

                clienteEncontrado = new DatosCliente(idCliente, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correo, telefono);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por idCliente: " + e.getMessage());
        }

        return clienteEncontrado;
    }

    @Override
    public int ModificarDatosCliente(DatosCliente datosCliente) {
        String consulta = "UPDATE datoscliente SET dni = ?, cuil = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, localidad = ?, provincia = ?, correo = ?, telefono = ? WHERE idCliente = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setInt(1, datosCliente.getDni());
            statement.setString(2, datosCliente.getCuil());
            statement.setString(3, datosCliente.getNombre());
            statement.setString(4, datosCliente.getApellido());
            statement.setString(5, datosCliente.getSexo());
            statement.setString(6, datosCliente.getNacionalidad());

            // Conversión de java.util.Date a java.sql.Date
            java.util.Date fechaUtil = datosCliente.getFechaNacimiento();
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            statement.setDate(7, fechaSql);

            statement.setString(8, datosCliente.getDireccion());
            statement.setString(9, datosCliente.getLocalidad());
            statement.setString(10, datosCliente.getProvincia());
            statement.setString(11, datosCliente.getCorreo());
            statement.setString(12, datosCliente.getTelefono());
            statement.setInt(13, datosCliente.getIdCliente());

            int filasActualizadas = statement.executeUpdate();

            return filasActualizadas;
        } catch (SQLException e) {
            System.err.println("Error al modificar datos del cliente: " + e.getMessage());
        }

        return 0;
    }


}
