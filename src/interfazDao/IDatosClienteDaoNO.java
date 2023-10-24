package interfazDao;

import entidad.DatosCliente;

public interface IDatosClienteDaoNO {
	public int Agregar(DatosCliente idCliente);
	public DatosCliente BuscarPorIdCliente(int idCliente);
	public int ModificarDatosCliente(DatosCliente idCliente);
}
