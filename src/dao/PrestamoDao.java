package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Prestamo;
import entidad.Prestamo.Estado;
import interfazDao.IPrestamoDao;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class PrestamoDao implements IPrestamoDao {
    private Connection conexion;

    public PrestamoDao() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public ArrayList<Prestamo> ListarTodos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM prestamo");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(resultSet.getInt("idPrestamo"));
                prestamo.setNumeroCuenta(resultSet.getInt("numeroCuenta"));
                prestamo.setIdCliente(resultSet.getInt("idCliente"));
                prestamo.setImportePedido(resultSet.getDouble("importePedido"));
                prestamo.setImportePorMes(resultSet.getDouble("importexmes"));
                prestamo.setCuotas(resultSet.getInt("cuotas"));
                prestamo.setFechaPedido(resultSet.getDate("fechaPedido").toLocalDate());

                int estadoNumerico = resultSet.getInt("estado");
                Estado estadoPrestamo = Estado.PENDIENTE;
                if (estadoNumerico == 1) {
                    estadoPrestamo = Estado.APROBADO;
                } else if (estadoNumerico == 2) {
                    estadoPrestamo = Estado.RECHAZADO;
                }
                prestamo.setEstado(estadoPrestamo);

                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todos los préstamos: " + e.getMessage());
        }

        return prestamos;
    }

    @Override
    public ArrayList<Prestamo> ListarPendientes() {
        ArrayList<Prestamo> prestamosPendientes = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM prestamo WHERE estado = 0");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(resultSet.getInt("idPrestamo"));
                prestamo.setNumeroCuenta(resultSet.getInt("numeroCuenta"));
                prestamo.setIdCliente(resultSet.getInt("idCliente"));
                prestamo.setImportePedido(resultSet.getDouble("importePedido"));
                prestamo.setImportePorMes(resultSet.getDouble("importexmes"));
                prestamo.setCuotas(resultSet.getInt("cuotas"));
                prestamo.setFechaPedido(resultSet.getDate("fechaPedido").toLocalDate());
                prestamo.setEstado(Estado.PENDIENTE); // Filtro solo los pendientes

                prestamosPendientes.add(prestamo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar préstamos pendientes: " + e.getMessage());
        }

        return prestamosPendientes;
    }

    @Override
    public int PedirPrestamo(Prestamo prestamo) {
        int resultado = 0;

        try (PreparedStatement statement = conexion.prepareStatement("INSERT INTO prestamo (numeroCuenta, idCliente, importePedido, importexmes, cuotas, fechaPedido, estado) VALUES (?, ?, ?, ?, ?, NOW(), 0)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, prestamo.getNumeroCuenta());
            statement.setInt(2, prestamo.getIdCliente());
            statement.setDouble(3, prestamo.getImportePedido());
            statement.setDouble(4, prestamo.getImportePorMes());
            statement.setInt(5, prestamo.getCuotas());

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al pedir un préstamo: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public int CambiarEstadoPrestamo(int idPrestamo, entidad.CuotaPrestamo.Estado estado) {
        int resultado = 0;

        try (PreparedStatement statement = conexion.prepareStatement("UPDATE prestamo SET estado = ? WHERE idPrestamo = ?")) {
            statement.setInt(1, estado.ordinal());
            statement.setInt(2, idPrestamo);

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al cambiar el estado de un préstamo: " + e.getMessage());
        }

        return resultado;
    }

}
