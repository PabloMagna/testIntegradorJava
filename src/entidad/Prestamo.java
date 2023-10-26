package entidad;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private int numeroCuenta;
    private int idCliente;
    private double importePedido;
    private double importePorMes;
    private int cuotas;
    private LocalDate fechaPedido;
    private Estado estado;
    
    public enum Estado{
    	PENDIENTE,
    	APROBADO,
    	RECHAZADO    	
    }
    
	public Prestamo() {}

	public Prestamo(int idPrestamo, int numeroCuenta, int idCliente, double importePedido, double importePorMes,
			int cuotas, LocalDate fechaPedido, Estado estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.numeroCuenta = numeroCuenta;
		this.idCliente = idCliente;
		this.importePedido = importePedido;
		this.importePorMes = importePorMes;
		this.cuotas = cuotas;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
	}

	public Prestamo(int numeroCuenta, int idCliente, double importePedido, double importePorMes, int cuotas,
			LocalDate fechaPedido, Estado estado) {
		super();
    	this.idPrestamo = 0;
		this.numeroCuenta = numeroCuenta;
		this.idCliente = idCliente;
		this.importePedido = importePedido;
		this.importePorMes = importePorMes;
		this.cuotas = cuotas;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public double getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(double importePedido) {
		this.importePedido = importePedido;
	}

	public double getImportePorMes() {
		return importePorMes;
	}

	public void setImportePorMes(double importePorMes) {
		this.importePorMes = importePorMes;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
	public void setEstado(int estado) {
		if(estado == 0)
			this.estado = Estado.PENDIENTE;
		else if(estado == 1)
			this.estado = Estado.APROBADO;
		else
			this.estado = Estado.RECHAZADO;
	}
	
}
