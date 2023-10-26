package entidad;

import java.time.LocalDate;

public class Cuenta {
    private int numero;
    private int idCliente;
    private String CBU;
    private double saldo;
    private LocalDate fecha;
    private int activo;
    private TipoCuenta tipoCuenta;

    public Cuenta() {
    }

    public Cuenta(int numero, int idCliente, String CBU, double saldo, LocalDate fecha, int activo, TipoCuenta tipoCuenta) {
        this.numero = numero;
        this.idCliente = idCliente;
        this.CBU = CBU;
        this.saldo = saldo;
        this.fecha = fecha;
        this.activo = activo;
        this.tipoCuenta = tipoCuenta;
    }
    public Cuenta(int idCliente, String CBU, double saldo, LocalDate fecha, int activo, TipoCuenta tipoCuenta) {
        this.numero = 0;
        this.idCliente = idCliente;
        this.CBU = CBU;
        this.saldo = saldo;
        this.fecha = fecha;
        this.activo = activo;
        this.tipoCuenta = tipoCuenta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU(String CBU) {
        this.CBU = CBU;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
}
