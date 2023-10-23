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
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO cuenta (idCliente, numero, CBU, saldo, fecha, activo, idTipoCuenta) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            
            // Establecer los valores de los parámetros
            statement.setInt(1, cuenta.getIdCliente());
            statement.setString(2, cuenta.getNumero());
            statement.setString(3, cuenta.getCBU());
            statement.setDouble(4, 10000.00); // Se asigna un monto inicial de $10,000
            statement.setDate(5, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
            statement.setInt(6, 1);
            statement.setInt(7, cuenta.getTipoCuenta().getIdTipoCuenta()); // Asume que tienes un objeto TipoCuenta con un id
            
            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar la cuenta: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }

        return resultado;
    }

    @Override
    public ArrayList<Cuenta> ListarCuentasActivas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT c.*, tc.descripcion FROM cuenta c " +
                         "INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta " +
                         "WHERE c.activo = 1";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("idCuenta"));
                cuenta.setIdCliente(resultSet.getInt("idCliente"));
                cuenta.setNumero(resultSet.getString("numero"));
                cuenta.setCBU(resultSet.getString("CBU"));
                cuenta.setSaldo(resultSet.getDouble("saldo"));
                cuenta.setFecha(resultSet.getDate("fecha"));
                cuenta.setActivo(resultSet.getInt("activo"));

                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                tipoCuenta.setDescripcion(resultSet.getString("descripcion"));
                
                cuenta.setTipoCuenta(tipoCuenta);

                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas activas: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion(conexion);
        }

        return cuentas;
    }

    @Override
    public ArrayList<Cuenta> ListarPorIdCliente(int idCliente) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT c.*, tc.descripcion FROM cuenta c " +
                         "INNER JOIN tiposcuenta tc ON c.idTipoCuenta = tc.idTipoCuenta " +
                         "WHERE c.idCliente = ? and c.activo = 1";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(resultSet.getInt("idCuenta"));
                cuenta.setIdCliente(resultSet.getInt("idCliente"));
                cuenta.setNumero(resultSet.getString("numero"));
                cuenta.setCBU(resultSet.getString("CBU"));
                cuenta.setSaldo(resultSet.getDouble("saldo"));
                cuenta.setFecha(resultSet.getDate("fecha"));
                cuenta.setActivo(resultSet.getInt("activo"));

                TipoCuenta tipoCuenta = new TipoCuenta();
                tipoCuenta.setIdTipoCuenta(resultSet.getInt("idTipoCuenta"));
                tipoCuenta.setDescripcion(resultSet.getString("descripcion"));
                
                cuenta.setTipoCuenta(tipoCuenta);

                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuentas por idCliente: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion(conexion);
        }

        return cuentas;
    }

    @Override
    public int ModificarCuenta(Cuenta cuenta) {
        int resultado = 0;
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE cuenta SET idCliente = ?, numero = ?, CBU = ?, saldo = ?, fecha = ?, activo = ?, idTipoCuenta = ? WHERE idCuenta = ?";
            statement = conexion.prepareStatement(sql);

            // Establecer los valores de los parámetros
            statement.setInt(1, cuenta.getIdCliente());
            statement.setString(2, cuenta.getNumero());
            statement.setString(3, cuenta.getCBU());
            statement.setDouble(4, cuenta.getSaldo());
            statement.setDate(5, new java.sql.Date(cuenta.getFecha().getTime()));
            statement.setInt(6, cuenta.getActivo());
            statement.setInt(7, cuenta.getTipoCuenta().getIdTipoCuenta());
            statement.setInt(8, cuenta.getIdCuenta());

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar la cuenta: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }

        return resultado;
    }

    @Override
    public int EliminarCuenta(int idCuenta) {
        int resultado = 0;
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE cuenta SET activo = 0 WHERE idCuenta = ?";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCuenta);

            resultado = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al realizar la baja lógica de la cuenta: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }

        return resultado;
    }


    @Override
    public int CantidadCuentasCliente(int idCliente) {
        int cantidadCuentas = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT COUNT(*) AS cantidad FROM cuenta WHERE idCliente = ? and Activo = 1";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCliente);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cantidadCuentas = resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de cuentas del cliente: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el resultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar el statement: " + e.getMessage());
                }
            }
        }

        return cantidadCuentas;
    }

}

