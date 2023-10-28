package utilidades;
import java.util.Map;


public class ReporteMovimientos {
    private Map<String, Double> totalPorTipoMovimiento; // Mapa para almacenar el total por tipo de movimiento
    private double totalGeneral; // Total general de importe
    private int mesFiltrado; // Mes seleccionado para el filtro
    private int añoFiltrado; // Año seleccionado para el filtro

    public Map<String, Double> getTotalPorTipoMovimiento() {
        return totalPorTipoMovimiento;
    }

    public void setTotalPorTipoMovimiento(Map<String, Double> totalPorTipoMovimiento) {
        this.totalPorTipoMovimiento = totalPorTipoMovimiento;
    }

    public double getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(double totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public int getMesFiltrado() {
        return mesFiltrado;
    }

    public void setMesFiltrado(int mesFiltrado) {
        this.mesFiltrado = mesFiltrado;
    }

    public int getAñoFiltrado() {
        return añoFiltrado;
    }

    public void setAñoFiltrado(int añoFiltrado) {
        this.añoFiltrado = añoFiltrado;
    }
}
