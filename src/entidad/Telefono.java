package entidad;

public class Telefono {
	int idTelefono;
	int idCliente;
	String numero;
	int activo;
	
	public Telefono(int idTelefono, int idCliente, String numero, int activo) {
		super();
		this.idTelefono = idTelefono;
		this.idCliente = idCliente;
		this.numero = numero;
		this.activo = activo;
	}
	public Telefono(int idCliente, String numero, int activo) {
		super();
		this.idTelefono = 0;
		this.idCliente = idCliente;
		this.numero = numero;
		this.activo = activo;
	}
	public Telefono() {}
	
	public int getIdTelefono() {
		return idTelefono;
	}
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
}
