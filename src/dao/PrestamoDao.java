package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

        try (PreparedStatement statement = conexion.prepareStatement("INSERT INTO prestamo (numeroCuenta, idCliente, importePedido, importexmes, cuotas, fechaPedido, estado) VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, prestamo.getNumeroCuenta());
            statement.setInt(2, prestamo.getIdCliente());
            statement.setDouble(3, prestamo.getImportePedido());
            
            //IMPORTE CON 10% intereses mensual
            double importeMensual = (prestamo.getImportePedido()*1.1)/prestamo.getCuotas();
            statement.setDouble(4, importeMensual);
            
            statement.setInt(5, prestamo.getCuotas());

            // Establece la fecha actual
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            
            // Establece el estado como 0 (pendiente)
            statement.setInt(7, 0);

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al pedir un préstamo: " + e.getMessage());
        }

        return resultado;
    }


    @Override
    public int CambiarEstadoPrestamo(int idPrestamo, Estado estado) {
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

    @Override
    public Prestamo ObtenerPrestamoPorId(int idPrestamo) {
        Prestamo prestamo = null;

        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM prestamo WHERE idPrestamo = ?")) {
            statement.setInt(1, idPrestamo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    prestamo = new Prestamo();
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
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener un préstamo por ID: " + e.getMessage());
        }

        return prestamo;
    }

    @Override
    public ArrayList<Prestamo> ListarPorClienteAprobados(int idCliente) {
        ArrayList<Prestamo> prestamosAprobados = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(
                "SELECT p.idPrestamo, p.numeroCuenta, p.idCliente, p.importePedido, p.importexmes, " +
                "p.cuotas, p.fechaPedido, p.estado " +
                "FROM prestamo p " +
                "JOIN cuenta c ON p.numeroCuenta = c.numero " +
                "WHERE p.idCliente = ? " +
                "AND p.estado = 1 " +
                "AND c.activo = 1")) {
            statement.setInt(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamo(resultSet.getInt("idPrestamo"));
                    prestamo.setNumeroCuenta(resultSet.getInt("numeroCuenta"));
                    prestamo.setIdCliente(resultSet.getInt("idCliente"));
                    prestamo.setImportePedido(resultSet.getDouble("importePedido"));
                    prestamo.setImportePorMes(resultSet.getDouble("importexmes"));
                    prestamo.setCuotas(resultSet.getInt("cuotas"));
                    prestamo.setFechaPedido(resultSet.getDate("fechaPedido").toLocalDate());
                    prestamo.setEstado(Estado.APROBADO);

                    prestamosAprobados.add(prestamo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar préstamos aprobados con cuentas activas para un cliente: " + e.getMessage());
        }
        return prestamosAprobados;
    }

}
