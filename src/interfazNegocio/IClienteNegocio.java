package interfazNegocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface IClienteNegocio {
	public Cliente Login(String usuario, String constrasenia);
	public int Agregar(Cliente cliente);
	public ArrayList<Cliente> ListarClientesActivos();
	public Cliente ObtenerPorIdCliente(int idCliente);
	public boolean ModificarCliente(Cliente cliente);
	public boolean EliminarCliente(int idCliente);
}
