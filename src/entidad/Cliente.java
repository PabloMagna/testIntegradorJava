package entidad;

import java.util.Date;

public class Cliente {
    private int idCliente;
    private String usuario;
    private String contrasena;
    private int dni;
    private int activo;
    private Date fecha;
    private int idTipo;
    
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public Cliente(int idCliente, String usuario, String contrasena, int dni, int activo, Date fecha2, int idTipo) {
		super();
		this.idCliente = idCliente;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.dni = dni;
		this.activo = activo;
		this.fecha = fecha2;
		this.idTipo = idTipo;
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	
    
}

