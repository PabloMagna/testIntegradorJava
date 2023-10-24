package negocio;

import dao.DatosClienteDaoNO;
import entidad.DatosCliente;
import interfazNegocio.IDatosClienteNegocioNO;

public class DatosClienteNegocio implements IDatosClienteNegocioNO {
	DatosClienteDaoNO dao = new DatosClienteDaoNO();

	@Override
	public boolean Agregar(DatosCliente datosCliente) {
		if(dao.Agregar(datosCliente)>0) {
			return true;
		}
		return false;
	}

	@Override
	public DatosCliente BuscarPorIdCliente(int idCliente) {
		return dao.BuscarPorIdCliente(idCliente);
	}

	@Override
	public boolean ModificarDatosCliente(DatosCliente datosCliente) {
		return dao.ModificarDatosCliente(datosCliente) == 0 ? false:true;
	}

}
