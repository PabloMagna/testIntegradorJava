package entidad;

import java.util.Date;

public class Movimiento {
    private int idMovimiento;
    private String detalle;
    private double importe;
    private int idTipoMovimiento;
    private Date fecha;
    private String CBU;
	public Movimiento(int idMovimiento, String detalle, double importe, int idTipoMovimiento, Date fecha, String cBU) {
		super();
		this.idMovimiento = idMovimiento;
		this.detalle = detalle;
		this.importe = importe;
		this.idTipoMovimiento = idTipoMovimiento;
		this.fecha = fecha;
		CBU = cBU;
	}
	public Movimiento( String detalle, double importe, int idTipoMovimiento, Date fecha, String cBU) {
		super();
		this.idMovimiento = 0;
		this.detalle = detalle;
		this.importe = importe;
		this.idTipoMovimiento = idTipoMovimiento;
		this.fecha = fecha;
		CBU = cBU;
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	} 
}
