package nc.dao.memory;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.ItemMenuDAO;
import nc.modelo.Bebida;
import nc.modelo.Categoria;
import nc.modelo.ItemMenu;
import nc.util.compareStrategies.itemMenu.CompareItemMenuStrategyInterface;
import nc.modelo.Plato;
import nc.modelo.Vendedor;
import nc.excepciones.ItemNoEncontradoException;
import nc.util.compareStategies.itemMenu.implementations.NameComp;
import nc.util.compareStategies.itemMenu.implementations.PriceComp;

public class ItemMenuMemory implements ItemMenuDAO {

    private static ItemMenuMemory uniqueInstance;
    private ArrayList<ItemMenu> memory;
    /////////////////////////////////////////////////////////////////////
    ArrayList<ItemMenu> items = new ArrayList<>();

    public ArrayList<ItemMenu> listar() {
        return items;
    }

    /*
    @Override
    public void add(ItemMenu item) {
        items.add(item);
    }*/

    public void actualizar(ItemMenu item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                return;
            }
        }
    }

    public void eliminar(int id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
                return;
            }
        }
    }

    public ItemMenu buscar(int id) {
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).getId() == id) {
                return memory.get(i);
            }
        }
        return null;
    }
///////////////////////////////////////////////////////////////////////////////

    private class ItemWrapper implements Comparable {

        ItemMenu item;
        CompareItemMenuStrategyInterface strategy;

        ItemWrapper(ItemMenu item) {
            this.item = item;
            this.strategy = new PriceComp(item, true);
        }

        public void setStrategy(CompareItemMenuStrategyInterface cmp) {
            this.strategy = cmp;
        }

        public ItemMenu unWrap() {
            return item;
        }

        @Override
        public int compareTo(Object o) {
            return this.strategy.compareTo(((ItemWrapper) o).unWrap());
        }

    }

    private ItemMenuMemory() {
        this.memory = new ArrayList<>();
    }

    public static ItemMenuMemory getInstancia() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemMenuMemory();
        }
        return uniqueInstance;
    }

    public void crearPlato(String nombre, Vendedor vendedor, float precio, float peso, float calorias) {
        ItemMenu item = new Plato(nombre, vendedor, precio, peso, calorias);
        add(item);
    }

    public void crearBebida(String nombre, Vendedor vendedor, float precio, float pes, int grado, float tam) {
        ItemMenu item = new Bebida(nombre, vendedor, precio, pes, grado, tam);
        add(item);
    }

    @Override
    public void add(ItemMenu item) {
        memory.add(item);
    }

    @Override
    public void addAll(List<ItemMenu> items) {
        memory.addAll(items);
    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = new ArrayList();
        switch (tipoFiltro) {
            case VENDEDOR ->
                filtrado = memory.stream().filter(item -> item.getVendedor() == (Vendedor) filtro).collect(Collectors.toList());
            case NOMBRE_VENDEDOR ->
                filtrado = memory.stream().filter(item -> item.getVendedor().getNombre().equals((String) filtro)).collect(Collectors.toList());
            case ID_VENDEDOR ->
                filtrado = memory.stream().filter(item -> item.getVendedor().getId() == ((Integer) filtro)).collect(Collectors.toList());
            case ID ->
                filtrado = memory.stream().filter(item -> item.getId() == (int) filtro).collect(Collectors.toList());
            case CATEGORIA ->
                filtrado = memory.stream().filter(item -> item.tieneCategoria((Categoria) filtro)).collect(Collectors.toList());
            case CATEGORIA_EXCLUYENTE ->
                filtrado = memory.stream().filter(item -> (item.tieneCategoria((Categoria) filtro) && (item.cantidadCategorias() <= ((Categoria.categoriaBebidas == (Categoria) filtro || Categoria.categoriaPlatos == (Categoria) filtro) ? 1 : 2)))).collect(Collectors.toList());
            case CATEGORIAS ->
                filtrado = memory.stream().filter(item -> item.tieneCategorias((List<Categoria>) filtro)).collect(Collectors.toList());
            case PRECIO_TOPE ->
                filtrado = memory.stream().filter(item -> item.getPrecio() <= (float) filtro).collect(Collectors.toList());
            case PRECIO_MINIMO ->
                filtrado = memory.stream().filter(item -> item.getPrecio() >= (float) filtro).collect(Collectors.toList());

        }
        if (filtrado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        }
        return filtrado;
    }

    @Override
    public List<ItemMenu> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = this.filtrarPor(tipoFiltro, filtro);
        return this.ordenar(tipoOrden, filtrado.stream().map(item -> new ItemWrapper(item)).collect(Collectors.toList()), ascendente);
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = new ArrayList();
        switch (tipoFiltrado) {
            case PRECIO ->
                filtrado = memory.stream().filter(item -> item.getPrecio() >= (float) piso && item.getPrecio() <= (float) tope).collect(Collectors.toList());
        }
        if (filtrado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        }
        return filtrado;
    }

    @Override
    public List<ItemMenu> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = this.filtrarRango(tipoFiltrado, piso, tope);
        return (this.ordenar(tipoOrden, filtrado.stream().map(item -> new ItemWrapper(item)).collect(Collectors.toList()), ascendente));
    }

    private List<ItemMenu> ordenar(tipoOrdenamiento orden, List<ItemWrapper> items, boolean ascendente) {
        List<ItemWrapper> ordered = items;
        switch (orden) {
            case PRECIO ->
                ordered.stream().forEach(itp -> itp.setStrategy(new PriceComp(itp.unWrap(), ascendente)));
            case NOMBRE ->
                ordered.stream().forEach(itp -> itp.setStrategy(new NameComp(itp.unWrap(), ascendente)));
        }
        sort(ordered);
        return ordered.stream().map(item -> item.unWrap()).collect(Collectors.toList());
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = (List<ItemMenu>) this.memory.clone();
        for (int i = 0; i < tipoFiltros.size(); i++) {
            filtrado.retainAll(this.filtrarPor(tipoFiltros.get(i), filtros.get(i)));

        }
        return filtrado;
    }

    @Override
    public List<ItemMenu> filtroMultiple(List<tipoFiltrado> tipoFiltros, List<Object> filtros, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        List<ItemMenu> filtrado = this.filtroMultiple(tipoFiltros, filtros);
        return this.ordenar(tipoOrden, filtrado.stream().map(item -> new ItemWrapper(item)).collect(Collectors.toList()), ascendente);

    }

    @Override
    public ItemMenu getItem(int id) throws ItemNoEncontradoException {
        try {
            return this.filtrarPor(tipoFiltrado.ID, id).getFirst();
        } catch (ItemNoEncontradoException e) {
            throw e;
        }

    }

    @Override
    public List<ItemMenu> getAll() {
        return (List<ItemMenu>) memory.clone();
    }

    @Override
    public void update(int ID, ItemMenu item) throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int ID) throws ItemNoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
