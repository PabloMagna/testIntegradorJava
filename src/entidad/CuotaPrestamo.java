package entidad;

import java.util.Date;

public class CuotaPrestamo {
    private int idCuota;
    private int idPrestamo;
    private double importe;
    private int nDeCuota;
    private Date fecha;
	public CuotaPrestamo(int idCuota, int idPrestamo, double importe, int nDeCuota, Date fecha) {
		super();
		this.idCuota = idCuota;
		this.idPrestamo = idPrestamo;
		this.importe = importe;
		this.nDeCuota = nDeCuota;
		this.fecha = fecha;
	}
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
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
	public int getnDeCuota() {
		return nDeCuota;
	}
	public void setnDeCuota(int nDeCuota) {
		this.nDeCuota = nDeCuota;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
