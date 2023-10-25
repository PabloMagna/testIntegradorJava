package entidad;

import java.time.LocalDate;

public class CuotaPrestamo {
	private int nCuota;
	private int idPrestamo;
	private double importe;
	private LocalDate fechaPago;
	private Estado estado;

	
	public enum Estado{
		IMPAGO,
		PAGO
	}
	
	public CuotaPrestamo() {}

	public CuotaPrestamo(int nCuota, int idPrestamo, double importe, LocalDate fechaPago, Estado estado) {
		super();
		this.nCuota = nCuota;
		this.idPrestamo = idPrestamo;
		this.importe = importe;
		this.fechaPago = fechaPago;
		this.estado = estado;
	}

	public int getnCuota() {
		return nCuota;
	}

	public void setnCuota(int nCuota) {
		this.nCuota = nCuota;
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

}
