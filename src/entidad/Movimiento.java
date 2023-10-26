package entidad;

import java.time.LocalDate;


public class Movimiento {
    private int idMovimiento;
    private int numeroCuenta;
    private String detalle;
    private double importe;
    private TipoMovimiento idTipoMovimiento;
    private LocalDate fecha;
    
    public Movimiento() {}

	public Movimiento(int idMovimiento, int numeroCuenta, String detalle, double importe,
			TipoMovimiento idTipoMovimiento, LocalDate fecha) {
		super();
		this.idMovimiento = idMovimiento;
		this.numeroCuenta = numeroCuenta;
		this.detalle = detalle;
		this.importe = importe;
		this.idTipoMovimiento = idTipoMovimiento;
		this.fecha = fecha;
	}

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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

	public TipoMovimiento getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public void setIdTipoMovimiento(TipoMovimiento idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
    
}
