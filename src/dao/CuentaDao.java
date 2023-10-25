package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Cuenta;
import entidad.TipoCuenta;
import interfazDao.ICuentaDao;

public class CuentaDao implements ICuentaDao {
    private Connection conexion;

    public CuentaDao() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public int Agregar(Cuenta cuenta) {
        int resultado = 0;

        try (PreparedStatement statement = conexion.prepareStatement("INSERT INTO CUENTA (idCliente, numero, CBU, saldo, fecha, activo, idTipoCuenta) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, cuenta.getIdCliente());
            statement.setInt(2, cuenta.getNumero());
            statement.setString(3, cuenta.getCBU());
            statement.setDouble(4, cuenta.getSaldo());
            statement.setDate(5, java.sql.Date.valueOf(cuenta.getFecha()));
            statement.setInt(6, cuenta.getActivo());
            statement.setInt(7, cuenta.getTipoCuenta().getIdTipoCuenta());

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar la cuenta: " + e.getMessage());
        }

        return resultado;
    }

    @Override
    public ArrayList<Cuenta> ListarCuentasActivas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM CUENTA c INNER JOIN TIPOSCUENTA tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.activo = 1");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCliente(resultSet.getInt("idCliente"));
                cuenta.setNumero(resultSet.getInt("numero"));
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

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM CUENTA c INNER JOIN TIPOSCUENTA tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.idCliente = ? and c.activo = 1")) {
            statement.setInt(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setIdCliente(resultSet.getInt("idCliente"));
                    cuenta.setNumero(resultSet.getInt("numero"));
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

        try (PreparedStatement statement = conexion.prepareStatement("UPDATE CUENTA SET saldo = ?, idTipoCuenta = ? WHERE numero = ?")) {
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

        try (PreparedStatement statement = conexion.prepareStatement("UPDATE CUENTA SET activo = 0 WHERE numero = ?")) {
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

        try (PreparedStatement statement = conexion.prepareStatement("SELECT COUNT(*) AS cantidad FROM CUENTA WHERE idCliente = ? and Activo = 1")) {
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

    // M�todos para generar un CBU �nico de 22 caracteres (garantizando unicidad) y verificar la existencia de CBU
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
        try (PreparedStatement statement = conexion.prepareStatement("SELECT COUNT(*) AS count FROM CUENTA WHERE CBU = ?")) {
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

    public Cuenta ObtenerPorNumeroCuenta(int numeroCuenta) {
        Cuenta cuenta = null;

        try (PreparedStatement statement = conexion.prepareStatement("SELECT c.*, tc.descripcion FROM CUENTA c INNER JOIN TIPOSCUENTA tc ON c.idTipoCuenta = tc.idTipoCuenta WHERE c.numero = ?")) {
            statement.setInt(1, numeroCuenta);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cuenta = new Cuenta();
                    cuenta.setIdCliente(resultSet.getInt("idCliente"));
                    cuenta.setNumero(resultSet.getInt("numero"));
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
}
