package interfazDao;

import java.util.ArrayList;

import entidad.CuotaPrestamo.Estado;
import entidad.Prestamo;

public interface IPrestamoDao {
	public ArrayList<Prestamo> ListarTodos();
	public ArrayList<Prestamo> ListarPendientes();
	public int PedirPrestamo(Prestamo prestamo);
	public int CambiarEstadoPrestamo(int idPrestamo, Estado estado);
}
