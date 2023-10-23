package negocio;

import dao.DatosClienteDao;
import entidad.DatosCliente;
import interfazNegocio.IDatosClienteNegocio;

public class DatosClienteNegocio implements IDatosClienteNegocio {
	DatosClienteDao dao = new DatosClienteDao();

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
