package nc.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nc.config.DBConnector;
import nc.dao.VendedorDAO;
import nc.modelo.Vendedor;

public class VendedorJDBC implements VendedorDAO {

    private Connection conn = DBConnector.getInstance();

    @Override
    public ArrayList<Vendedor> listar() {
        ArrayList<Vendedor> lista = new ArrayList<>();
        String query = "SELECT * FROM vendedor";
        try (Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id"); // regularizar tema de id?
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double cx = rs.getDouble("cx");
                double cy = rs.getDouble("cy");
                Vendedor v = new Vendedor(id, nombre, direccion, cx, cy);
                lista.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    @Override
    public void add(Vendedor vendedor) {
        String query = "INSERT INTO vendedor (nombre, direccion, cx, cy) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getDireccion());
            ps.setDouble(3, vendedor.getCoordenada().getLatitude());
            ps.setDouble(4, vendedor.getCoordenada().getLongitude());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actualizar(Vendedor vendedor) {
        String query = "UPDATE vendedor SET nombre = ?, direccion = ?, cx = ?, cy = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getDireccion());
            ps.setDouble(3, vendedor.getCoordenada().getLatitude());
            ps.setDouble(4, vendedor.getCoordenada().getLongitude());
            ps.setInt(5, vendedor.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM vendedor WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Vendedor buscar(int id) {
        String query = "SELECT * FROM vendedor WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                double cx = rs.getDouble("cx");
                double cy = rs.getDouble("cy");
                return new Vendedor(id, nombre, direccion, cx, cy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Vendedor crear(String nombre, String direccion, double cx, double cy) {
        String query = "INSERT INTO vendedor (nombre, direccion, cx, cy) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setDouble(3, cx);
            ps.setDouble(4, cy);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new Vendedor(id, nombre, direccion, cx, cy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
