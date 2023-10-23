package interfazNegocio;

import java.util.ArrayList;

import entidad.Cuenta;

public interface ICuentaNegocio {
	public boolean Agregar(Cuenta cuenta);
	public ArrayList<Cuenta> ListarCuentasActivas();
	public ArrayList<Cuenta> ListarPorIdCliente(int idCliente);
	public boolean ModificarCuenta(Cuenta cuenta);
	public boolean EliminarCuenta(int idCuenta);
	public int CantidadCuentasCliente(int idCliente);
}
