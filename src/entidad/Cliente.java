package entidad;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;




public class Cliente {
    private int idCliente; // Clave primaria (PK)
    private String usuario;
    private String contrasena;
    private int activo;
    private LocalDate fechaCreacion; // Fecha de creación
    private TipoCliente tipoCliente; // Enum para el tipo de cliente
    private int dni; // Debe ser único
    private String cuil;
    private String nombre;
    private String apellido;
    private Sexo sexo; // Enum para el sexo
    private String nacionalidad;
    private LocalDate fechaNacimiento; // Fecha de nacimiento
    private String direccion;
    private Localidad localidad; // Objeto Localidad
    private Provincia provincia; // Objeto Provincia
    private String correo;
    private ArrayList<Telefono> telefonos; // Lista de teléfonos
    
    public enum TipoCliente {
        CLIENTE, // 0
        ADMIN    // 1
    }

    public enum Sexo {
        VARON,     // 0
        MUJER,     // 1
        INDEFINIDO // 2
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    // Constructor que inicializa algunos campos, puedes añadir más campos según sea necesario
    public Cliente() {
        this.activo = 1; // Por defecto, activo
        this.tipoCliente = TipoCliente.CLIENTE; // Por defecto, tipo "CLIENTE"
        this.sexo = Sexo.INDEFINIDO; // Por defecto, sexo "No Contesta"
    }

	public Cliente(int idCliente, String usuario, String contrasena, int activo, LocalDate fechaCreacion,
			TipoCliente tipoCliente, int dni, String cuil, String nombre, String apellido, Sexo sexo,
			String nacionalidad, LocalDate fechaNacimiento, String direccion, Localidad localidad, Provincia provincia,
			String correo, ArrayList<Telefono> telefonos) {
		super();
		this.idCliente = idCliente;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.tipoCliente = tipoCliente;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correo = correo;
		this.telefonos = telefonos;
	}

	public Cliente(String usuario, String contrasena, int activo, LocalDate fechaCreacion, TipoCliente tipoCliente, int dni,
			String cuil, String nombre, String apellido, Sexo sexo, String nacionalidad, LocalDate fechaNacimiento,
			String direccion, Localidad localidad, Provincia provincia, String correo, ArrayList<Telefono> telefonos) {
		super();
    	this.idCliente = 0;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.tipoCliente = tipoCliente;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correo = correo;
		this.telefonos = telefonos;
	}
    
}
