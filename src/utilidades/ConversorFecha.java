package utilidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConversorFecha {
    public static Date convertirCadenaAFecha(String fechaStr, String formatoEntrada) throws ParseException {
        SimpleDateFormat formatoEntradaFecha = new SimpleDateFormat(formatoEntrada);
        SimpleDateFormat formatoSalidaFecha = new SimpleDateFormat("yyyy-MM-dd");
        
        // Parsea la cadena en un objeto Date
        java.util.Date fechaParseada = formatoEntradaFecha.parse(fechaStr);
        
        // Formatea la fecha en el formato de salida "yyyy-MM-dd"
        String fechaFormateada = formatoSalidaFecha.format(fechaParseada);
        
        // Convierte la fecha formateada en un objeto Date
        Date fechaConvertida = Date.valueOf(fechaFormateada);
        
        return fechaConvertida;
    }
}
