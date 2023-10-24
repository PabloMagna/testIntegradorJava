package interfazNegocio;

import entidad.DatosCliente;

public interface IDatosClienteNegocioNO {
	public boolean Agregar(DatosCliente datosCliente);
	public DatosCliente BuscarPorIdCliente(int idCliente);
	public boolean ModificarDatosCliente(DatosCliente datoscliente);
}
