package entidad;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class PruebaFecha {
    private int id;
    private Date fecha;

    // Constructor con argumentos
    public PruebaFecha(int id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    // Constructor sin argumentos (si es necesario)
    public PruebaFecha() {
        // Constructor sin argumentos
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/y");
        return formato.format(this.fecha);
    }


    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
}
