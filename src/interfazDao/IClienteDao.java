package interfazDao;

import entidad.Cliente;

public interface IClienteDao {
	public Cliente Login(String usuario, String constrasenia);
}
