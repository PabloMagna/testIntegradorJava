package interfazDao;

import java.util.ArrayList;

import entidad.Telefono;

public interface ITelefonoDao {
	public ArrayList<Telefono> ListarTelefonoPorIdCliente(int idCliente);
	public int AgregarTelefono(int idCliente, String numero);
	public int ModificarTelefono(int idTelefono, String nuevoNumero);
	public int EliminarTeléfono(int idTelefono);
}
