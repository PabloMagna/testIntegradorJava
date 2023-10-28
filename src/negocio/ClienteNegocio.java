package negocio;

import java.util.ArrayList;

import dao.ClienteDao;
import entidad.Cliente;
import interfazNegocio.IClienteNegocio;

public class ClienteNegocio implements IClienteNegocio {
	private ClienteDao dao = new ClienteDao();

	@Override
	public Cliente Login(String usuario, String contrasenia) {    
        Cliente cliente = dao.Login(usuario, contrasenia);
        return cliente; // Devuelve el objeto Cliente si es diferente de null
    }

	@Override
	public int Agregar(Cliente cliente) {
		return dao.Agregar(cliente);
	}

	@Override
	public ArrayList<Cliente> ListarClientesActivos(String busqueda) {
		return dao.ListarClientesActivos(busqueda);
	}
	public Cliente ObtenerPorIdCliente(int idCliente) {
		return dao.ObtenerPorIdCliente(idCliente);
	}

	@Override
	public boolean ModificarCliente(Cliente cliente) {
		return dao.ModificarCliente(cliente) == 0 ? false : true;
	}

	@Override
	public boolean EliminarCliente(int idCliente) {
		return dao.EliminarCliente(idCliente) == 0 ? false : true;
	}
	
}
