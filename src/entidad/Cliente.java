package entidad;

import java.util.Date;

public class Cliente {
    private int idCliente;
    private String usuario;
    private String contrasena;
    private int activo;
    private Date fecha;
    private tipoCliente tipoCliente;

    public enum tipoCliente {
        CLIENTE, // 0
        ADMIN    // 1
    }

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

    public tipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(tipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public void setTipoCliente(int idTipo)
    {
    	if(idTipo == 0) {
    		this.tipoCliente = tipoCliente.CLIENTE;
    	} else {
    		this.tipoCliente = tipoCliente.ADMIN;
    	}
    }
    public Cliente(int idCliente, String usuario, String contrasena, int activo, Date fecha2, tipoCliente tipoCliente) {
        super();
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = activo;
        this.fecha = fecha2;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
        // Constructor vacío
    }
}
