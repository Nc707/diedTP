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
import nc.dao.ItemMenuDAO;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.Bebida;
import nc.modelo.ItemMenu;
import nc.modelo.Plato;

public class ItemMenuJDBC implements ItemMenuDAO {
    private Connection conn = DBConnector.getInstance();

    @Override
    public List<ItemMenu> getAll(){
        return null;//no implementado
    }

    @Override
    public void add(ItemMenu item){

    }

    @Override
    public void addAll(List<ItemMenu> items){

    }

    @Override
    public ItemMenu getItem(int ID) throws ItemNoEncontradoException{
        return null;//no implementado

    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException {
        List<ItemMenu> items = new ArrayList<>();
        String query = "SELECT * FROM item_menu WHERE ";
    

        //VER ESTO PORQUE ESTAN MAL LOS CASES
        switch (tipoFiltro) {
            case VENDEDOR:
            //IDK
                break;
            case NOMBRE_VENDEDOR:
               //HAY Q HACER UN JOIN CON VENDEDOR
                break;
            case ID_VENDEDOR:
                query += "id_vendedor = ?";
                break;
            case ID:
                query += "id = ?";
                break;
            case CATEGORIA:
                // DE LA TABLA TIENE_CATEGORIA
                break;
            case CATEGORIA_EXCLUYENTE:
                //IDK
                break;
            case CATEGORIAS:
                //DE LA TABLA CATEGORIA categoria IN (?)
            
                break;
            case PRECIO_TOPE:
                query += "precio <= ?";
                break;
            case PRECIO_MINIMO:
                query += "precio >= ?";
                break;
        }
    
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setObject(1, filtro);  
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                if(rs.getBoolean("es_bebida")){
                    ItemMenu item = new Bebida(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("id_vendedor"),
                        rs.getFloat("peso"),
                        rs.getInt("grado"),
                        rs.getFloat("tam")
                    );
                    items.add(item);

                } else {
                    ItemMenu item = new Plato(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("id_vendedor"),
                        rs.getFloat("peso"),
                        rs.getFloat("calorias")
                    );
                    items.add(item);
                }

            }
    
            if (items.isEmpty()) {
                throw new ItemNoEncontradoException("No se encontraron items con el filtro especificado.");
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al filtrar items", ex);
        }
    
        return items;
    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        return null;//no implementado
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException{
        return null;//no implementado
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        return null;//no implementado
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros) throws ItemNoEncontradoException{
        return null;//no implementado
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        return null;//no implementado
    }


    

    
}
