package nc.modelo.ItemMenuDAOandMemory;


import nc.modelo.excepciones.ItemNoEncontradoException;
import java.util.List;
import nc.modelo.ItemMenu;
import nc.modelo.interfacesPackage.GenericDAO;

public interface ItemMenuDAO extends GenericDAO<ItemMenu>{

    enum tipoFiltrado{
        VENDEDOR,
        NOMBRE_VENDEDOR,
        ID_VENDEDOR,
        ID,
        CATEGORIA,
        CATEGORIA_EXCLUYENTE,
        CATEGORIAS,
        PRECIO_TOPE,
        PRECIO_MINIMO,
        
    }
    enum tipoFiltradoRango{
        PRECIO   
    }
    enum tipoOrdenamiento{
        PRECIO,
        NOMBRE
    }
    public List<ItemMenu> getAll();
    @Override
    public void add(ItemMenu item);
    public void addAll(List<ItemMenu> items);
    public ItemMenu getItem(int ID) throws ItemNoEncontradoException;
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException;
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope)throws ItemNoEncontradoException;
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente)throws ItemNoEncontradoException;
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros) throws ItemNoEncontradoException;
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;
}
