package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidad.Provincia;
import interfazDao.IProvinciaDao;

public class ProvinciaDao implements IProvinciaDao {
    private Connection conexion;

    public ProvinciaDao() {
        conexion = Conexion.obtenerConexion();
    }

    @Override
    public ArrayList<Provincia> ListarProvincias() {
        ArrayList<Provincia> provincias = new ArrayList<>();

        String consulta = "SELECT id, nombre FROM provincias"; // Ajusta la consulta seg�n tu estructura de base de datos

        try (PreparedStatement statement = conexion.prepareStatement(consulta);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Provincia provincia = new Provincia();
                provincia.setId(resultSet.getInt("id"));
                provincia.setNombre(resultSet.getString("nombre"));
                provincias.add(provincia);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar provincias: " + e.getMessage());
        }

        return provincias;
    }
}
