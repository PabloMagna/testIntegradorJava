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
	public int Agregar(Cuenta cuenta) {
		if(CantidadCuentasCliente(cuenta.getIdCliente())<3) {
			return dao.Agregar(cuenta);
		} else {
			return -1;
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

	@Override
	public Cuenta ObtenerPorCbu(String cbu) {
		return dao.ObtenerPorCbu(cbu);
	}

	@Override
	public boolean SumarSaldo(int numeroCuenta, double saldo) {
		return dao.SumarSaldo(numeroCuenta,saldo)==0?false:true;
	}
}
