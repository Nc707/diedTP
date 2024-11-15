package nc.dao;

import java.util.List;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.ItemMenu;

public interface ItemMenuDAO {

    enum tipoFiltrado {
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

    enum tipoFiltradoRango {
        PRECIO
    }

    enum tipoOrdenamiento {
        PRECIO,
        NOMBRE
    }

    public List<ItemMenu> getAll();

    public void add(ItemMenu item);

    public void update(int ID, ItemMenu item) throws ItemNoEncontradoException;

    public void delete(int ID) throws ItemNoEncontradoException;

    public void addAll(List<ItemMenu> items);

    public List<ItemMenu> listarPorVendedor(int idVendedor) throws ItemNoEncontradoException;

    public ItemMenu getItem(int ID) throws ItemNoEncontradoException;

    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException;

    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;

    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException;

    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;

    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros) throws ItemNoEncontradoException;

    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;
}
