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
import nc.dao.ClienteDAO;
import nc.modelo.Cliente;       


public class ClienteJDBC implements ClienteDAO{
    private Connection conn = DBConnector.getInstance();
    @Override
    public ArrayList<Cliente> listar(){
        ArrayList<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try(Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id"); // regularizar tema de id?
                String nombre = rs.getString("nombre");
                int cuit = rs.getInt("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                double cx = rs.getDouble("cx");
                double cy = rs.getDouble("cy");
                Cliente c = new Cliente(id, nombre, cuit, email, direccion, cx, cy);
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void add(Cliente cliente){
        String query = "INSERT INTO cliente (nombre, cuit, email, direccion, cx, cy) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getCuit());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getDireccion());
            ps.setDouble(5, cliente.getCoordenada().getLatitude());
            ps.setDouble(6, cliente.getCoordenada().getLongitude());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void actualizar(Cliente cliente){
        String query = "UPDATE cliente SET nombre = ?, cuit = ?, email = ?, direccion = ?, cx = ?, cy = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getCuit());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getDireccion());
            ps.setDouble(5, cliente.getCoordenada().getLatitude());
            ps.setDouble(6, cliente.getCoordenada().getLongitude());
            ps.setInt(7, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void eliminar(int id){
        String query = "DELETE FROM cliente WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public Cliente buscar(int id){
        String query = "SELECT * FROM cliente WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String nombre = rs.getString("nombre");
                int cuit = rs.getInt("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                double cx = rs.getDouble("cx");
                double cy = rs.getDouble("cy");
                return new Cliente(id, nombre, cuit, email, direccion, cx, cy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
