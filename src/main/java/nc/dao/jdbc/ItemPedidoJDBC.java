package nc.dao.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nc.config.DBConnector;
import nc.dao.ItemPedidoDAO;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.ItemMenu;
import nc.modelo.ItemPedido;
import nc.modelo.Pedido;

public class ItemPedidoJDBC implements ItemPedidoDAO {
    private Connection conn = DBConnector.getInstance();

    @Override
    public List<ItemPedido> getAll() {
        throw new UnsupportedOperationException("Unimplemented method");

    }

    @Override
    public void add(ItemPedido item) {
        String query = "INSERT INTO item_pedido (cantidad, precio, id_item_menu, id_pedido) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, item.getCantidad());
            ps.setFloat(2, item.getPrecio());
            ps.setInt(3, item.getItemMenu().getId());
            ps.setInt(4, item.getPedido().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(int ID, ItemPedido item) throws ItemNoEncontradoException {

        String query = "UPDATE item_pedido SET cantidad = ?, precio = ?, id_item_menu = ?, id_pedido = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, item.getCantidad());
            ps.setFloat(2, item.getPrecio());
            ps.setInt(3, item.getItemMenu().getId());
            ps.setInt(4, item.getPedido().getId());
            ps.setInt(5, ID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public void delete(int ID) throws ItemNoEncontradoException {

        String query = "DELETE FROM item_pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void addAll(List<ItemPedido> items) {

        for (ItemPedido item : items) {
            add(item);
        }

    }

    @Override
    public ItemPedido getItem(int ID) throws ItemNoEncontradoException {
        String query = "SELECT * FROM item_pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                float precio = rs.getFloat("precio");
                int id_item_menu = rs.getInt("id_item_menu");
                int id_pedido = rs.getInt("id_pedido");
                ItemMenu itemMenu = new ItemMenuJDBC().getItem(id_item_menu);
                Pedido pedido = new PedidoJDBC().buscar(id_pedido);
                return new ItemPedido(ID, itemMenu, cantidad, precio, pedido);
            } else {
                throw new ItemNoEncontradoException("No se encontró el item con ID " + ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public List<ItemPedido> listarPorPedido(int id_pedido) {
        String query = "SELECT * FROM item_pedido WHERE id_pedido = ?";
        List<ItemPedido> items = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id_pedido);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("id");
                int cantidad = rs.getInt("cantidad");
                float precio = rs.getFloat("precio");
                int id_item_menu = rs.getInt("id_item_menu");

                ItemMenu itemMenu = new ItemMenuJDBC().getItem(id_item_menu);
                Pedido pedido = new PedidoJDBC().buscar(id_pedido);

                
                items.add(new ItemPedido(ID, itemMenu, cantidad, precio, pedido));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ItemNoEncontradoException e) {
            System.out.println(e.getMessage()); 
        }
        return items;
    }



    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarPor'");
    }

    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden,
            boolean ascendente) throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarPor'");
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope)
            throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarRango'");
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope,
            tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarRango'");
    }


}
