package entidad;

import java.time.LocalDate;

public class Cuota {
	private int numeroCuota;
	private int idPrestamo;
	private double importe;
	private LocalDate fechaPago;
	private Estado estado;

	
	public enum Estado{
		IMPAGO,
		PAGO
	}
	
	public Cuota() {}

	public Cuota(int nCuota, int idPrestamo, double importe, LocalDate fechaPago, Estado estado) {
		super();
		this.numeroCuota = nCuota;
		this.idPrestamo = idPrestamo;
		this.importe = importe;
		this.fechaPago = fechaPago;
		this.estado = estado;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int nCuota) {
		this.numeroCuota = nCuota;
	}

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

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setEstado(int estado) {
		this.estado = estado == 1? Estado.PAGO:Estado.IMPAGO;
	}

}
