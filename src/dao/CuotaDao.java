package dao;

import entidad.Cuota;
import entidad.Cuota.Estado;
import interfazDao.ICuotaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CuotaDao implements ICuotaDao {
	private Connection conexion;

	public CuotaDao() {
		conexion = Conexion.obtenerConexion();
	}

	@Override
	public int GenerarCuotas(int idPrestamo, double importe, int cantidadCuotas) {
		String query = "INSERT INTO cuota (nCuota, idPrestamo, importe, fechaPago, estado) VALUES (?, ?, ?, NULL, 0)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			for (int nCuota = 1; nCuota <= cantidadCuotas; nCuota++) {
				pstmt.setInt(1, nCuota);
				pstmt.setInt(2, idPrestamo);
				pstmt.setDouble(3, importe);
				pstmt.addBatch(); // Agregar la sentencia a un lote para mejorar el rendimiento
			}

			int[] resultados = pstmt.executeBatch(); // Ejecutar todas las inserciones

			// Verificar el número de filas afectadas
			for (int resultado : resultados) {
				if (resultado == PreparedStatement.EXECUTE_FAILED) {
					// Manejar error
					return 0;
				}
			}

			return resultados.length; // Devuelve la cantidad de cuotas insertadas
		} catch (SQLException e) {
			// Manejar excepciones apropiadamente
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int PagarCuota(int numeroCuota, int idPrestamo) {
		String query = "UPDATE cuota SET fechaPago = CURRENT_DATE, estado = ? WHERE nCuota = ? AND idPrestamo = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, 1); // 1 representa el estado PAGO en la base de datos
			pstmt.setInt(2, numeroCuota);
			pstmt.setInt(3, idPrestamo);

			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Cuota obtenerCuotaPorNumeroYID(int numeroCuota, int idPrestamo) {
		String query = "SELECT * FROM cuota WHERE nCuota = ? AND idPrestamo = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, numeroCuota);
			pstmt.setInt(2, idPrestamo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int numeroCuotaResult = rs.getInt("nCuota");
					int idPrestamoResult = rs.getInt("idPrestamo");
					double importe = rs.getDouble("importe");
					LocalDate fechaPago = (rs.getDate("fechaPago") != null) ? rs.getDate("fechaPago").toLocalDate()
							: null;
					int estado = rs.getInt("estado");

					// Mapear el estado a un valor del enum
					Estado estadoCuota = (estado == 1) ? Estado.PAGO : Estado.IMPAGO;

					return new Cuota(numeroCuotaResult, idPrestamoResult, importe, fechaPago, estadoCuota);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Cuota> ListarPorIdPrestamo(int idPrestamo) {
		ArrayList<Cuota> cuotas = new ArrayList<>();
		String query = "SELECT * FROM cuota WHERE idPrestamo = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, idPrestamo);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int numeroCuota = rs.getInt("nCuota");
					double importe = rs.getDouble("importe");
					LocalDate fechaPago = (rs.getDate("fechaPago") != null) ? rs.getDate("fechaPago").toLocalDate()
							: null;
					int estado = rs.getInt("estado");

					// Mapear el estado a un valor del enum
					Estado estadoCuota = (estado == 1) ? Estado.PAGO : Estado.IMPAGO;

					Cuota cuota = new Cuota(numeroCuota, idPrestamo, importe, fechaPago, estadoCuota);
					cuotas.add(cuota);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuotas;
	}
	
	/* Select anterior (no importa si la cuenta raíz existe o no).
	 
	 	 "SELECT c.nCuota, c.idPrestamo, c.importe, c.fechaPago, c.estado " +
                       "FROM cuota AS c " +
                       "JOIN prestamo AS p ON c.idPrestamo = p.idPrestamo " +
                       "WHERE p.idCliente = ? " +
                       "AND p.estado = 1 " +
                       "AND c.estado = 0";
	 
	  */
	//Aca tenemos que ver si es necesario que la cuenta a la que se le prestó tenga que estar activa para filtrarla, lo hice.
	//Pero no estoy seguro que la deuda no se mantenga a pesar de que la cuenta ya no existe.
	@Override
	public ArrayList<Cuota> ListarPorClienteAprobadoEImpago(int idCliente) {
		ArrayList<Cuota> cuotas = new ArrayList<>();
		String query = "SELECT c.nCuota, c.idPrestamo, c.importe, c.fechaPago, c.estado " + "FROM cuota AS c "
				+ "JOIN prestamo AS p ON c.idPrestamo = p.idPrestamo "
				+ "JOIN cuenta AS cu ON p.numeroCuenta = cu.numero " + "WHERE p.idCliente = ? " + "AND p.estado = 1 "
				+ "AND cu.activo = 1 " + "AND c.estado = 0";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, idCliente);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int numeroCuota = rs.getInt("nCuota");
					int idPrestamo = rs.getInt("idPrestamo");
					double importe = rs.getDouble("importe");
					LocalDate fechaPago = (rs.getDate("fechaPago") != null) ? rs.getDate("fechaPago").toLocalDate()
							: null;
					int estado = rs.getInt("estado");

					Estado estadoCuota = (estado == 1) ? Estado.PAGO : Estado.IMPAGO;

					Cuota cuota = new Cuota(numeroCuota, idPrestamo, importe, fechaPago, estadoCuota);
					cuotas.add(cuota);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuotas;
	}

}
