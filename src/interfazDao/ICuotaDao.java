package interfazDao;

import java.util.ArrayList;

import entidad.Cuota;

public interface ICuotaDao {
	public int GenerarCuotas(int idPrestamo, double importe, int cantidadCoutas);
	public int PagarCuota(int numeroCuota, int idPrestamo);
	public Cuota obtenerCuotaPorNumeroYID(int numeroCuota, int idPrestamo);
	public ArrayList<Cuota> ListarPorIdPrestamo(int idPrestamo);
	public ArrayList<Cuota> ListarPorClienteAprobadoEImpago(int idCliente);
}
