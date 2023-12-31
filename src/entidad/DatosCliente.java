package entidad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class DatosCliente {
	private int idCliente;
    private int dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private Date fechaNacimiento;
    private String direccion;
    private String localidad;
    private String provincia;
    private String correo;
    private String telefono;

    // Constructor vac�o
    public DatosCliente() {
    }

    // Constructor con todos los campos
    public DatosCliente(int idCliente,int dni, String cuil, String nombre, String apellido, String sexo, String nacionalidad, Date fechaNacimiento, String direccion, String localidad, String provincia, String correo, String telefono) {
        this.idCliente = idCliente;
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
        this.telefono = telefono;
    }

    // Getters y setters para todos los campos
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(java.util.Date fechaNacimiento) {
        this.fechaNacimiento = (fechaNacimiento != null) ? new Date(fechaNacimiento.getTime()) : null;
    }
    public void setFechaNacimientoFromString(String fechaNacimientoStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha esperado
        Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
        this.fechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setIdCliente(int idCliente) {
    	this.idCliente = idCliente;
    }
    public int getIdCliente() {
    	return this.idCliente;
    }
}
