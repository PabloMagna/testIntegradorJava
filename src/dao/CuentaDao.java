package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Cuenta;
import entidad.TipoCuenta;
import interfazDao.ICuentaDao;
import java.time.LocalDate;

public class CuentaDao implements ICuentaDao {
    private Connection conexion;

    public CuentaDao() {
        conexion = Conexion.obtenerConexion();
    }

    public int Agregar(Cuenta cuenta) {
        int numeroCuenta = 0; // Inicializa la variable que almacenar� el n�mero de cuenta generado

        // Genera un CBU �nico (reemplaza esta parte con tu l�gica de generaci�n)
        String cbu = generarCBUUnico();

        // Obtener la fecha actual como LocalDate
        LocalDate fechaActual = LocalDate.now();

        try (PreparedStatement statement = conexion.prepareStatement("INSERT INTO cuenta (idCliente, CBU, saldo, fecha, activo, idTipoCuenta) VALUES (?, ?, ?, ?, 1, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Establece los par�metros para la inserci�n
            statement.setInt(1, cuenta.getIdCliente());
            statement.setString(2, cbu);
            statement.setDouble(3, 10000); // Valor fijo de 10,000
            statement.setObject(4, fechaActual);
            statement.setInt(5, cuenta.getTipoCuenta().getIdTipoCuenta());

            // Ejecuta la inserci�n
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                // Si la inserci�n fue exitosa, obt�n el n�mero de cuenta generado
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    numeroCuenta = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar la cuenta: " + e.getMessage());
        }

        // Devuelve el n�mero de cuenta generado
        return numeroCuenta;
    }



    public Cuenta ObtenerPorNumeroCuenta(int numeroCuenta) {
        Cuenta cuenta = null;

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM cuenta c INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.numero = ? and activo = 1")) {
            statement.setInt(1, numeroCuenta);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cuenta = new Cuenta();
                    cuenta.setNumero(resultSet.getInt("numero"));
                    cuenta.setIdCliente(resultSet.getInt("idCliente"));
                    cuenta.setCBU(resultSet.getString("CBU"));
                    cuenta.setSaldo(resultSet.getDouble("saldo"));
                    cuenta.setFecha(resultSet.getDate("fecha").toLocalDate());
                    cuenta.setActivo(resultSet.getInt("activo"));

                    TipoCuenta tipoCuenta = new TipoCuenta();
                    tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                    tipoCuenta.setDescripcion(resultSet.getString("descripcion"));

                    cuenta.setTipoCuenta(tipoCuenta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cuenta por n�mero de cuenta: " + e.getMessage());
        }

        return cuenta;
    }

    @Override
    public ArrayList<Cuenta> ListarCuentasActivas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM cuenta c INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.activo = 1");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumero(resultSet.getInt("numero"));
                cuenta.setIdCliente(resultSet.getInt("idCliente"));
                cuenta.setCBU(resultSet.getString("CBU"));
                cuenta.setSaldo(resultSet.getDouble("saldo"));
                cuenta.setFecha(resultSet.getDate("fecha").toLocalDate());
                cuenta.setActivo(resultSet.getInt("activo"));

                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                tipoCuenta.setDescripcion(resultSet.getString("descripcion"));

                cuenta.setTipoCuenta(tipoCuenta);

                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas activas: " + e.getMessage());
        }

        return cuentas;
    }

    @Override
    public ArrayList<Cuenta> ListarPorIdCliente(int idCliente) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM cuenta c INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.idCliente = ? and c.activo = 1")) {
            statement.setInt(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setNumero(resultSet.getInt("numero"));
                    cuenta.setIdCliente(resultSet.getInt("idCliente"));
                    cuenta.setCBU(resultSet.getString("CBU"));
                    cuenta.setSaldo(resultSet.getDouble("saldo"));
                    cuenta.setFecha(resultSet.getDate("fecha").toLocalDate());
                    cuenta.setActivo(resultSet.getInt("activo"));

                    TipoCuenta tipoCuenta = new TipoCuenta();
                    tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                    tipoCuenta.setDescripcion(resultSet.getString("descripcion"));

                    cuenta.setTipoCuenta(tipoCuenta);

                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas por idCliente: " + e.getMessage());
        }
        return cuentas;
    }

    @Override
    public int ModificarCuenta(Cuenta cuenta) {
        int resultado = 0;

        try (PreparedStatement statement = conexion.prepareStatement("UPDATE cuenta SET saldo = ?, idTipoCuenta = ? WHERE numero = ?")) {
            statement.setDouble(1, cuenta.getSaldo());
            statement.setInt(2, cuenta.getTipoCuenta().getIdTipoCuenta());
            statement.setInt(3, cuenta.getNumero());

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar la cuenta: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public int EliminarCuenta(int numero) {
        int resultado = 0;

        try (PreparedStatement statement = conexion.prepareStatement("UPDATE cuenta SET activo = 0 WHERE numero = ?")) {
            statement.setInt(1, numero);
            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al realizar la baja l�gica de la cuenta: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public int CantidadCuentasCliente(int idCliente) {
        int cantidadCuentas = 0;

        try (PreparedStatement statement = conexion.prepareStatement("SELECT COUNT(*) AS cantidad FROM cuenta WHERE idCliente = ? and activo = 1")) {
            statement.setInt(1, idCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cantidadCuentas = resultSet.getInt("cantidad");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de cuentas del cliente: " + e.getMessage());
        }

        return cantidadCuentas;
    }
    private String generarCBUUnico() {
        String cbu = "";
        boolean cbuUnico = false;

        while (!cbuUnico) {
            // Generar un CBU �nico de 22 d�gitos aleatorios
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 22; i++) {
                int randomDigit = (int) (Math.random() * 10);
                sb.append(randomDigit);
            }
            cbu = sb.toString();

            // Verificar si el CBU generado es �nico
            if (!existeCBU(cbu)) {
                cbuUnico = true;
            }
        }

        return cbu;
    }

    // M�todo para verificar si un CBU ya existe en la base de datos
    private boolean existeCBU(String cbu) {
        try (PreparedStatement statement = conexion.prepareStatement("SELECT COUNT(*) AS count FROM cuenta WHERE CBU = ?")) {
            statement.setString(1, cbu);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del CBU: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Cuenta ObtenerPorCbu(String cbu) {
        Cuenta cuenta = null;

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM cuenta c INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.CBU = ? and c.activo = 1")) {
            statement.setString(1, cbu);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cuenta = new Cuenta();
                    cuenta.setNumero(resultSet.getInt("numero"));
                    cuenta.setIdCliente(resultSet.getInt("idCliente"));
                    cuenta.setCBU(resultSet.getString("CBU"));
                    cuenta.setSaldo(resultSet.getDouble("saldo"));
                    cuenta.setFecha(resultSet.getDate("fecha").toLocalDate());
                    cuenta.setActivo(resultSet.getInt("activo"));

                    TipoCuenta tipoCuenta = new TipoCuenta();
                    tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                    tipoCuenta.setDescripcion(resultSet.getString("descripcion"));

                    cuenta.setTipoCuenta(tipoCuenta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cuenta por CBU: " + e.getMessage());
        }

        return cuenta;
    }

}
