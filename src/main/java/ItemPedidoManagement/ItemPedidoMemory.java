package ItemPedidoManagement;

import static ItemPedidoManagement.ItemPedidoDAO.tipoFiltrado.PEDIDO;
import nc.diedtp.ItemPedido;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.StreamSupport.stream;
import nc.diedtp.Cliente;
import nc.diedtp.ItemMenu;
import nc.diedtp.Pedido;
import nc.diedtp.Vendedor;
import nc.diedtp.excepciones.*;

public class ItemPedidoMemory implements ItemPedidoDAO {
    
    private ArrayList<ItemPedido> memory;
    public ItemPedidoMemory() {
        this.memory = new ArrayList<>();
    }
    public void addPedido(List<ItemPedido> items) {
        memory.addAll(items);
    }
    public void removePedido(Pedido p) throws ItemNoEncontradoException{
        List<ItemPedido> elementsToRemove = this.filtrarPor(PEDIDO, p);
        memory.removeAll(elementsToRemove);
        
    }
    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = new ArrayList();
        switch(tipoFiltro){
            case PEDIDO -> filtrado = memory.stream().filter(item->item.getPedido() == (Pedido)filtro).collect(Collectors.toList());
            case VENDEDOR -> filtrado = memory.stream().filter(item->item.getVendedor() == (Vendedor)filtro).collect(Collectors.toList());
            case CLIENTE -> filtrado = memory.stream().filter(item->item.getPedido().getCliente() == (Cliente)filtro).collect(Collectors.toList());
            case ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu() == (ItemMenu)filtro).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >=  (float)filtro).collect(Collectors.toList());
        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        //switch()
        //filtrado = filtrado.stream().forEach(item->item.setStrategy())
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarPor(tipoFiltro, filtro);
        return this.ordenar(tipoOrden, filtrado);
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = new ArrayList();
        switch(tipoFiltrado){
            case PRECIO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >= (float) piso && item.getPrecio() <= (float)tope).collect(Collectors.toList());
            case PRECIO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >= (float) piso && item.getItemMenu().getPrecio() <= (float)tope).collect(Collectors.toList());

        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarRango(tipoFiltrado, piso, tope);
        return this.ordenar(tipoOrden, filtrado);
    }
    private List<ItemPedido> ordenar(tipoOrdenamiento orden,List<ItemPedido> items){
         List<ItemPedido> ordered = new ArrayList();
         switch(orden){
             case PRECIO_ITEMPEDIDO -> items.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp)));
             case CLIENTE_ID -> items.stream().forEach(itp -> itp.setStrategy(new ClienteIdComp(itp)));
         }
         sort(ordered);
         return ordered;
    }
/*
    
    public ArrayList<Pedido> getListaItemsPedido() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedido) {
        this.listaPedidos = listaPedido;
    }

    

    @Override
    public ArrayList<ItemPedido> busquedaPorPrecio(float piso, float tope){
        ArrayList<ItemPedido> aux = new ArrayList<>();
        String cant, nombreItemMenu, precio;

        for (Pedido ped : listaPedidos) {
            for (ItemPedido item : ped.getItemsPedido()) {
                if ((item.getPrecio() >= piso) && (item.getPrecio() <= tope)) {
                    aux.add(item);
                    cant = String.valueOf(item.getCantidad());
                    nombreItemMenu = item.getItemMenu().getNombre();
                    precio = String.valueOf(item.getPrecio());
                }
            }
        }
        return aux;
    }

    @Override
    public ArrayList<ItemPedido> busquedaPorVendedor(int idVendedor) throws ItemNoEncontradoException {
        ArrayList<ItemPedido> aux = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            for (ItemPedido item : pedido.getItemsPedido()) {
                if (item.getItemMenu().getVendedor().getId() == idVendedor) {
                    aux.add(item);
                }
            }
        }

        if (aux.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems para el vendedor con ID: " + idVendedor);
        }

        return aux;
    }

    @Override
    public ArrayList<ItemPedido> busquedaPorCliente(int id) throws ItemNoEncontradoException {
        // semanticamente no deberia lanzar esta excepcion porque no es que no encuentra items, sino que no encuentra el cliente
        ArrayList<ItemPedido> aux = new ArrayList<>();
        Cliente cliente = null;

        for (Pedido ped : listaPedidos) {
            if (id == ped.getCliente().getId()) {
                cliente = ped.getCliente();
                break;
            }
        }

        // Lanza excepcin si no se encuentra el cliente
        if (cliente == null) {
            throw new ItemNoEncontradoException("No se encontró un cliente con el ID: " + id);
        }


        for (Pedido ped : listaPedidos) {
            if (cliente.equals(ped.getCliente())) {
                aux.addAll(ped.getItemsPedido());
            }
        }

        return aux;
    }

    @Override
    public void filtrarCategoria() {

    }

    @Override
    public ArrayList<ItemPedido> OrdenarPorCantidadASC(int idPedido) throws PedidoNoEncontradoException {
        ArrayList<ItemPedido> aux = null;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getId() == idPedido) {
                aux = pedido.getItemsPedido();
                break;
            }
        }
        if (aux == null) {
            throw new PedidoNoEncontradoException("No se encontró un pedido con el ID: " + idPedido);
        }
        return mergeSort(aux);
    }

    private ArrayList<ItemPedido> mergeSort(ArrayList<ItemPedido> aux) {
        if (aux.size() <= 1) {
            return aux;
        }

        int medio = aux.size() / 2;
        ArrayList<ItemPedido> izq = new ArrayList<>(aux.subList(0, medio));
        ArrayList<ItemPedido> der = new ArrayList<>(aux.subList(medio, aux.size()));
        return merge(mergeSort(izq), mergeSort(der));
    }

    private ArrayList<ItemPedido> merge(ArrayList<ItemPedido> izq, ArrayList<ItemPedido> der) {
        int i = 0, j = 0; //i recorre el izq y j recorre el der;
        ArrayList<ItemPedido> resultado = new ArrayList<>();
        while (i < izq.size() && j < der.size()) {
            if (izq.get(i).getCantidad() <= der.get(j).getCantidad()) {
                resultado.add(izq.get(i));
                i++;
            } else {
                resultado.add(der.get(j));
                j++;
            }
        }
        while (i < izq.size()) {
            resultado.add(izq.get(i));
            i++;
        }

        while (j < der.size()) {
            resultado.add(der.get(j));
            j++;
        }

        return resultado;
    }*/

   
}
