package entidad;

import java.util.Date;

public class Transferencia {
    private int idTransferencia;
    private String CBUOrigen;
    private String CBUDestino;
    private double monto;
    private Date fecha;
	public Transferencia(int idTransferencia, String cBUOrigen, String cBUDestino, double monto, Date fecha) {
		super();
		this.idTransferencia = idTransferencia;
		CBUOrigen = cBUOrigen;
		CBUDestino = cBUDestino;
		this.monto = monto;
		this.fecha = fecha;
	}
	public Transferencia(String cBUOrigen, String cBUDestino, double monto, Date fecha) {
		super();
		this.idTransferencia=0;
		CBUOrigen = cBUOrigen;
		CBUDestino = cBUDestino;
		this.monto = monto;
		this.fecha = fecha;
	}
	public int getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public String getCBUOrigen() {
		return CBUOrigen;
	}
	public void setCBUOrigen(String cBUOrigen) {
		CBUOrigen = cBUOrigen;
	}
	public String getCBUDestino() {
		return CBUDestino;
	}
	public void setCBUDestino(String cBUDestino) {
		CBUDestino = cBUDestino;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
   
}
