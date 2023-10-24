package negocio;

import java.util.ArrayList;

import dao.CuentaDao;
import dao.TipoCuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;
import interfazNegocio.ICuentaNegocio;

public class CuentaNegocio implements ICuentaNegocio {
	CuentaDao dao = new CuentaDao();
	TipoCuentaDao tpDao = new TipoCuentaDao();

	@Override
	public boolean Agregar(Cuenta cuenta) {
		if(CantidadCuentasCliente(cuenta.getIdCliente())<3) {
			return dao.Agregar(cuenta) == 0? false:true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Cuenta> ListarCuentasActivas() {
		return dao.ListarCuentasActivas();
	}

	@Override
	public ArrayList<Cuenta> ListarPorIdCliente(int idCliente) {
		return dao.ListarPorIdCliente(idCliente);
	}

	@Override
	public boolean ModificarCuenta(Cuenta cuenta) {
		return dao.ModificarCuenta(cuenta) == 0? false:true;
	}

	@Override
	public boolean EliminarCuenta(int numeroCuenta) {
		return dao.EliminarCuenta(numeroCuenta)== 0? false:true;
	}

	@Override
	public int CantidadCuentasCliente(int idCliente) {
		return dao.CantidadCuentasCliente(idCliente);
	}
	public ArrayList<TipoCuenta> ListarTipoCuenta(){
		return tpDao.ListarTipoCuenta();
	}

	public Cuenta ObtenerPorNumeroCuenta(int numero) {
		// TODO Auto-generated method stub
		return dao.ObtenerPorNumeroCuenta(numero);
	}
}
