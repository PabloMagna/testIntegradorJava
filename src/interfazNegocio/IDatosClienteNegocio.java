package interfazNegocio;

import entidad.DatosCliente;

public interface IDatosClienteNegocio {
	public boolean Agregar(DatosCliente datosCliente);
	public DatosCliente BuscarPorIdCliente(int idCliente);
	public boolean ModificarDatosCliente(DatosCliente datoscliente);
}
