package interfazDao;

import entidad.DatosCliente;

public interface IDatosClienteDao {
	public int Agregar(DatosCliente idCliente);
	public DatosCliente BuscarPorIdCliente(int idCliente);
	public int ModificarDatosCliente(DatosCliente idCliente);
}
