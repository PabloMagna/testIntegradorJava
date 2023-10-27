package interfazDao;

import java.util.ArrayList;

import entidad.Cuenta;


public interface ICuentaDao {
	public int Agregar(Cuenta cuenta);
	public int SumarSaldo(int numeroCuenta, double saldo);
	public ArrayList<Cuenta> ListarCuentasActivas();
	public ArrayList<Cuenta> ListarPorIdCliente(int idCliente);
	public int ModificarCuenta(Cuenta cuenta);
	public int EliminarCuenta(int idCuenta);
	public int CantidadCuentasCliente(int idCliente);
	public Cuenta ObtenerPorNumeroCuenta(int numero);
	public Cuenta ObtenerPorCbu(String cbu);
}
