package interfazNegocio;

import java.util.ArrayList;

import entidad.Cuota;

public interface ICuotaNegocio {
	public boolean GenerarCuotas(int idPrestamo, double importe, int cantidadCuotas);
	public boolean PagarCuota(int numeroCuota, int idPrestamo);
	public Cuota obtenerCuotaPorNumeroYID(int numeroCuota, int idPrestamo);
	public ArrayList<Cuota> ListarPorIdPrestamo(int idPrestamo);
	public ArrayList<Cuota> ListarPorClienteAprobadoEImpago(int idCliente);
}
