package nc.dao.jdbc;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nc.config.DBConnector;
import nc.dao.PedidoDAO;
import nc.modelo.Cliente;
import nc.modelo.Pedido;
import nc.modelo.Pedido.EstadoPedido;
import nc.modelo.Vendedor;

public class PedidoJDBC implements PedidoDAO {

    private Connection conn = DBConnector.getInstance();

    @Override
    public ArrayList<Pedido> listar() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM pedido";
        try(Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                int id_vendedor = rs.getInt("id_vendedor");
                int id_cliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido p = new Pedido(id, id_vendedor, id_cliente, precio, estado);
                pedidos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return pedidos;
    }

    @Override
    public void add(Pedido pedido) {
        String query = "INSERT INTO pedido (id_vendedor, id_cliente, precio, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pedido.getVendedor().getId());
            ps.setInt(2, pedido.getCliente().getId());
            ps.setFloat(3, pedido.getPrecio());
            ps.setString(4, pedido.getEstado().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actualizar(Pedido pedido) {
        String query = "UPDATE pedido SET id_vendedor = ?, id_cliente = ?, precio = ?, estado = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pedido.getVendedor().getId());
            ps.setInt(2, pedido.getCliente().getId());
            ps.setFloat(3, pedido.getPrecio());
            ps.setString(4, pedido.getEstado().toString());
            ps.setInt(5, pedido.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Pedido buscar(int id) {
        String query = "SELECT * FROM pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id_vendedor = rs.getInt("id_vendedor");
                int id_cliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido pedido = new Pedido(id, id_vendedor, id_cliente, precio, estado);
                return pedido;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Pedido crear(Vendedor vendedor, Cliente cliente) {
        //no veo la utilidad de esto pero lo dejo porlas dudas
        Pedido pedido = new Pedido(vendedor, cliente);
        add(pedido);
        return pedido;

    }

    @Override
    public ArrayList<Pedido> listarPorVendedor(int idVendedor) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT p.id, p.id_vendedor, p.id_cliente, p.precio, p.estado FROM pedido p join vendedor v on p.id_vendedor = v.id where v.id = ?; ";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idVendedor);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int id_vendedor = rs.getInt("id_vendedor");
                int id_cliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido pedido = new Pedido(id, id_vendedor, id_cliente, precio, estado);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;

    }

    @Override
    public ArrayList<Pedido> listarPorCliente(int idCliente) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT p.id, p.id_vendedor, p.id_cliente, p.precio, p.estado FROM pedido p join cliente c on p.id_cliente = c.id where c.id = ?; ";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int id_vendedor = rs.getInt("id_vendedor");
                int id_cliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estado = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido pedido = new Pedido(id, id_vendedor, id_cliente, precio, estado);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;

    }

    @Override
    public ArrayList<Pedido> listarPorEstadoYVendedor(int id_vendedor, EstadoPedido estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT p.id, p.id_vendedor, p.id_cliente, p.precio, p.estado FROM pedido p join vendedor v on p.id_vendedor = v.id where v.id = ? and p.estado = ?; ";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id_vendedor);
            ps.setString(2, estado.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int idVendedor = rs.getInt("id_vendedor");
                int idCliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estadoPedido = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido pedido = new Pedido(id, idVendedor, idCliente, precio, estadoPedido);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;

    }

    @Override
    public ArrayList<Pedido> listarPorEstadoYCliente(int id_cliente, EstadoPedido estado) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT p.id, p.id_vendedor, p.id_cliente, p.precio, p.estado FROM pedido p join cliente c on p.id_cliente = c.id where c.id = ? and p.estado = ?; ";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id_cliente);
            ps.setString(2, estado.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int idVendedor = rs.getInt("id_vendedor");
                int idCliente = rs.getInt("id_cliente");
                float precio = rs.getFloat("precio");
                EstadoPedido estadoPedido = EstadoPedido.valueOf(rs.getString("estado"));
                Pedido pedido = new Pedido(id, idVendedor, idCliente, precio, estadoPedido);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }
    
}
