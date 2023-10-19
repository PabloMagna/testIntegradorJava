package negocio;

import dao.ClienteDao;
import interfazNegocio.IClienteNegocio;

public class ClienteNegocio implements IClienteNegocio{

	@Override
	public boolean Login(String usuario, String constrasenia) {
		ClienteDao dao = new ClienteDao();
		if(dao.Login(usuario, constrasenia)!=null){
			return true;
		}
		return false;
	}
	
}
