package negocio;

import java.util.ArrayList;

import dao.TipoCuentaDao;
import entidad.TipoCuenta;
import interfazNegocio.ITipoCuentaNegocio;

public class TipoCuentaNegocio implements ITipoCuentaNegocio {
	private TipoCuentaDao dao = new TipoCuentaDao();
	
	
	public ArrayList<TipoCuenta> Listar(){
		return dao.ListarTipoCuenta();
	}
	
}