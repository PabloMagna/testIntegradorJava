package interfazNegocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface ICuentaNegocio {
	public int Agregar(Cuenta cuenta);
	public ArrayList<Cuenta> ListarCuentasActivas();
	public ArrayList<Cuenta> ListarPorIdCliente(int idCliente);
	public boolean ModificarCuenta(Cuenta cuenta);
	public boolean EliminarCuenta(int idCuenta);
	public int CantidadCuentasCliente(int idCliente);
	public Cuenta ObtenerPorNumeroCuenta(int numero);
	public Cuenta ObtenerPorCbu(String cbu);
	public boolean SumarSaldo(int numeroCuenta, double saldo);
}
