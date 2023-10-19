package entidad;

import java.util.Date;

public class Cuenta {
    private int idCuenta;
    private int idCliente;
    private String numero;
    private String CBU;
    private double saldo;
    private Date fecha;
    private int activo;
    private TipoCuenta tipoCuenta;
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Cuenta(int idCuenta, int idCliente, String numero, String cBU, double saldo, Date fecha, int activo,
			TipoCuenta tipoCuenta) {
		super();
		this.idCuenta = idCuenta;
		this.idCliente = idCliente;
		this.numero = numero;
		CBU = cBU;
		this.saldo = saldo;
		this.fecha = fecha;
		this.activo = activo;
		this.tipoCuenta = tipoCuenta;
	}
	public Cuenta(int idCliente, String numero, String cBU, double saldo, Date fecha, int activo,
			TipoCuenta tipoCuenta) {
		super();
		this.idCuenta = 0;
		this.idCliente = idCliente;
		this.numero = numero;
		CBU = cBU;
		this.saldo = saldo;
		this.fecha = fecha;
		this.activo = activo;
		this.tipoCuenta = tipoCuenta;
	}
	public Cuenta() {}
    
}
