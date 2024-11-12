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
        List<ItemMenu> items = new ArrayList<>();
        String query = "SELECT im.id, im.nombre, im.descripcion, im.precio, im.id_vendedor, im.es_bebida,"+
                        "p.calorias, p.apto_celiaco, p.apto_vegano, p.peso,"+
                        "b.graduacion_alcoholica, b.tamaño AS tamaño_bebida, b.peso AS peso_bebida"+
                        "FROM item_menu im"+
                        "LEFT JOIN plato p ON p.id = im.id AND im.es_bebida = FALSE"+
                        "LEFT JOIN bebida b ON b.id = im.id AND im.es_bebida = TRUE;";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
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
                        rs.getFloat("grado"),
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

        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, null, ex);  
        }
         return items;
    }

    @Override
    public void add(ItemMenu item){
        String itemMenuSQL = "INSERT INTO item_menu (nombre, descripcion, precio, id_vendedor, es_bebida) VALUES (?, ?, ?, ?, ?);"; 
        try {
            // creamos una transaccion para prevenir errores en donde por ejemplo se inserta el itemMenu pero no la bebida o plato
            conn.setAutoCommit(false); // inicia transaccion
            try (PreparedStatement ps = conn.prepareStatement(itemMenuSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, item.getNombre());
                ps.setString(2, item.getDescripcion());
                ps.setFloat(3, item.getPrecio());
                ps.setInt(4, item.getVendedor().getId());
                ps.setBoolean(5, item.esBebida());
                ps.executeUpdate();
    
                if(item.esBebida()){
                    String bebidaSQL = "INSERT INTO bebida (id, graduacion_alcoholica, tamaño, peso) VALUES (?, ?, ?, ?);";
                    try (ResultSet rs = ps.getGeneratedKeys(); PreparedStatement psBebida = conn.prepareStatement(bebidaSQL)) {
                        rs.next();
                        int id = rs.getInt(1);
                        Bebida bebida = (Bebida) item;
                        psBebida.setInt(1, id);
                        psBebida.setFloat(2, bebida.getGraduacionAlcoholica());
                        psBebida.setFloat(3, bebida.getTamaño());
                        psBebida.setFloat(4, bebida.getPeso());
                        psBebida.executeUpdate();
                    }
                    
                }
                else{
                    String platoSQL = "INSERT INTO plato (id, calorias, apto_celiaco, apto_vegano, peso) VALUES (?, ?, ?, ?, ?);";
                    try(ResultSet rs = ps.getGeneratedKeys(); PreparedStatement psPlato = conn.prepareStatement(platoSQL)){
                        rs.next();
                        int id = rs.getInt(1);
                        Plato plato = (Plato) item;
                        psPlato.setInt(1, id);
                        psPlato.setFloat(2, plato.getCalorias());
                        psPlato.setBoolean(3, plato.isAptoCeliaco());
                        psPlato.setBoolean(4, plato.isAptoVegano());
                        psPlato.setFloat(5,plato.getPeso());
                        psPlato.executeUpdate();
                    }
                }
    
            }
            conn.commit(); // finaliza transaccion

        } catch (SQLException ex) {
            try {
                conn.rollback(); // rollback para errores
            } catch (SQLException rollbackEx) {
                Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Rollback failed", rollbackEx);
            }
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.setAutoCommit(true); // volvemos a autocommit
            } catch (SQLException e) {
                Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Failed to reset auto-commit", e);
            }
        }
        }

    @Override
    public void addAll(List<ItemMenu> items){
        for (ItemMenu item : items) {
            add(item);
        }
    }

    @Override
    public ItemMenu getItem(int ID) throws ItemNoEncontradoException{
        String query = "SELECT im.id, im.nombre, im.descripcion, im.precio, im.id_vendedor, im.es_bebida,"+
                        "p.calorias, p.apto_celiaco, p.apto_vegano, p.peso,"+
                        "b.graduacion_alcoholica, b.tamaño AS tamaño_bebida, b.peso AS peso_bebida"+
                        "FROM item_menu im"+
                        "LEFT JOIN plato p ON p.id = im.id AND im.es_bebida = FALSE"+
                        "LEFT JOIN bebida b ON b.id = im.id AND im.es_bebida = TRUE"+
                        "WHERE im.id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if(rs.getBoolean("es_bebida")){
                    return new Bebida(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("id_vendedor"),
                        rs.getFloat("peso"),
                        rs.getFloat("grado"),
                        rs.getFloat("tam")
                    );

                } else {
                    return new Plato(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("id_vendedor"),
                        rs.getFloat("peso"),
                        rs.getFloat("calorias")
                    );
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new ItemNoEncontradoException("No se encontró el item con ID: " + ID);

    }

    @Override
    public void update(int ID, ItemMenu item) throws ItemNoEncontradoException {
        String itemMenuSQL = "UPDATE item_menu SET nombre = ?, descripcion = ?, precio = ?, id_vendedor = ?, es_bebida = ? WHERE id = ?;";
        try {
            conn.setAutoCommit(false); // inicia transaccion
            try (PreparedStatement ps = conn.prepareStatement(itemMenuSQL)) {
                ps.setString(1, item.getNombre());
                ps.setString(2, item.getDescripcion());
                ps.setFloat(3, item.getPrecio());
                ps.setInt(4, item.getVendedor().getId());
                ps.setBoolean(5, item.esBebida());
                ps.setInt(6, ID);
                ps.executeUpdate();
    
                if(item.esBebida()){
                    String bebidaSQL = "UPDATE bebida SET graduacion_alcoholica = ?, tamaño = ?, peso = ? WHERE id = ?;";
                    try (PreparedStatement psBebida = conn.prepareStatement(bebidaSQL)) {
                        Bebida bebida = (Bebida) item;
                        psBebida.setFloat(1, bebida.getGraduacionAlcoholica());
                        psBebida.setFloat(2, bebida.getTamaño());
                        psBebida.setFloat(3, bebida.getPeso());
                        psBebida.setInt(4, ID);
                        psBebida.executeUpdate();
                    }
                    
                }
                else{
                    String platoSQL = "UPDATE plato SET calorias = ?, apto_celiaco = ?, apto_vegano = ?, peso = ? WHERE id = ?;";
                    try(PreparedStatement psPlato = conn.prepareStatement(platoSQL)){
                        Plato plato = (Plato) item;
                        psPlato.setFloat(1, plato.getCalorias());
                        psPlato.setBoolean(2, plato.isAptoCeliaco());
                        psPlato.setBoolean(3, plato.isAptoVegano());
                        psPlato.setFloat(4,plato.getPeso());
                        psPlato.setInt(5, ID);
                        psPlato.executeUpdate();
                    }
                }
    
            }
            conn.commit(); // finaliza transaccion

        } catch (SQLException ex) {
            try {
                conn.rollback(); // rollback para errores
            } catch (SQLException rollbackEx) {
                Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Rollback failed", rollbackEx);
            }
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.setAutoCommit(true); // volvemos a autocommit
            } catch (SQLException e) {
                Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Failed to reset auto-commit", e);
            }
        }

    }


    @Override
    public void delete(int ID) throws ItemNoEncontradoException {
        String itemMenuSQL = "DELETE FROM item_menu WHERE id = ?;";
            try (PreparedStatement ps = conn.prepareStatement(itemMenuSQL)) {
                ps.setInt(1, ID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet.");
        // List<ItemMenu> items = new ArrayList<>();
        // String query = "SELECT * FROM item_menu WHERE ";
    

        // //VER ESTO PORQUE ESTAN MAL LOS CASES
        // switch (tipoFiltro) {
        //     case VENDEDOR:
        //     //IDK
        //         break;
        //     case NOMBRE_VENDEDOR:
               
        //         break;
        //     case ID_VENDEDOR:
        //         query += "id_vendedor = ?";
        //         break;
        //     case ID:
        //         query += "id = ?";
        //         break;
        //     case CATEGORIA:
        //         // DE LA TABLA TIENE_CATEGORIA
        //         break;
        //     case CATEGORIA_EXCLUYENTE:
        //         //IDK
        //         break;
        //     case CATEGORIAS:
        //         //DE LA TABLA CATEGORIA categoria IN (?)
            
        //         break;
        //     case PRECIO_TOPE:
        //         query = "SELECT im.id, im.nombre, im.descripcion, im.precio, im.id_vendedor, im.es_bebida,"+
        //                 "p.calorias, p.apto_celiaco, p.apto_vegano, p.peso,"+
        //                 "b.graduacion_alcoholica, b.tamaño AS tamaño_bebida, b.peso AS peso_bebida"+
        //                 "FROM item_menu im"+
        //                 "LEFT JOIN plato p ON p.id = im.id AND im.es_bebida = FALSE"+
        //                 "LEFT JOIN bebida b ON b.id = im.id AND im.es_bebida = TRUE"+
        //                 "WHERE im.precio <= ?;"
        //  ;
        //         break;
        //     case PRECIO_MINIMO:
        //         query = "SELECT im.id, im.nombre, im.descripcion, im.precio, im.id_vendedor, im.es_bebida,"+
        //         "p.calorias, p.apto_celiaco, p.apto_vegano, p.peso,"+
        //         "b.graduacion_alcoholica, b.tamaño AS tamaño_bebida, b.peso AS peso_bebida"+
        //         "FROM item_menu im"+
        //         "LEFT JOIN plato p ON p.id = im.id AND im.es_bebida = FALSE"+
        //         "LEFT JOIN bebida b ON b.id = im.id AND im.es_bebida = TRUE"+
        //         "WHERE im.precio >= ?;";
        //         break;
        // }
    
        // try (PreparedStatement ps = conn.prepareStatement(query)) {
        //     ps.setObject(1, filtro);  
        //     ResultSet rs = ps.executeQuery();
    
        //     while (rs.next()) {
        //         if(rs.getBoolean("es_bebida")){
        //             ItemMenu item = new Bebida(
        //                 rs.getInt("id"),
        //                 rs.getString("nombre"),
        //                 rs.getString("descripcion"),
        //                 rs.getFloat("precio"),
        //                 rs.getInt("id_vendedor"),
        //                 rs.getFloat("peso"),
        //                 rs.getInt("grado"),
        //                 rs.getFloat("tam")
        //             );
        //             items.add(item);

        //         } else {
        //             ItemMenu item = new Plato(
        //                 rs.getInt("id"),
        //                 rs.getString("nombre"),
        //                 rs.getString("descripcion"),
        //                 rs.getFloat("precio"),
        //                 rs.getInt("id_vendedor"),
        //                 rs.getFloat("peso"),
        //                 rs.getFloat("calorias")
        //             );
        //             items.add(item);
        //         }

        //     }
    
        //     if (items.isEmpty()) {
        //         throw new ItemNoEncontradoException("No se encontraron items con el filtro especificado.");
        //     }
    
        // } catch (SQLException ex) {
        //     Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al filtrar items", ex);
        // }
    
        // return items;
    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros) throws ItemNoEncontradoException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        throw new UnsupportedOperationException("Not supported yet.");
    }




    

    
}
