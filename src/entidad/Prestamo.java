package entidad;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private double importe;
    private double importexmes;
    private int cuotas;
    private Date fecha;
    private int plazoPagoMeses;
    private int activo;
    private int estado;
    private String CBU;
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getImportexmes() {
		return importexmes;
	}
	public void setImportexmes(double importexmes) {
		this.importexmes = importexmes;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getPlazoPagoMeses() {
		return plazoPagoMeses;
	}
	public void setPlazoPagoMeses(int plazoPagoMeses) {
		this.plazoPagoMeses = plazoPagoMeses;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	}
	public Prestamo(int idPrestamo, double importe, double importexmes, int cuotas, Date fecha, int plazoPagoMeses,
			int activo, int estado, String cBU) {
		super();
		this.idPrestamo = idPrestamo;
		this.importe = importe;
		this.importexmes = importexmes;
		this.cuotas = cuotas;
		this.fecha = fecha;
		this.plazoPagoMeses = plazoPagoMeses;
		this.activo = activo;
		this.estado = estado;
		CBU = cBU;
	}
	public Prestamo(double importe, double importexmes, int cuotas, Date fecha, int plazoPagoMeses, int activo,
			int estado, String cBU) {
		super();
		this.idPrestamo = 0;
		this.importe = importe;
		this.importexmes = importexmes;
		this.cuotas = cuotas;
		this.fecha = fecha;
		this.plazoPagoMeses = plazoPagoMeses;
		this.activo = activo;
		this.estado = estado;
		CBU = cBU;
	}

   
}
