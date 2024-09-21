package nc.diedtp;

import java.util.ArrayList;
import nc.diedtp.excepciones.*;

public class ItemPedidoMemory implements ItemPedidoDAO {

    private ArrayList<Pedido> listaPedidos;

    public ItemPedidoMemory(ArrayList<Pedido> listaPedido) {
        this.listaPedidos = listaPedido;
    }

    public ArrayList<Pedido> getListaItemsPedido() {
        return listaPedidos;
    }

    public void setListaItemsPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedidos = listaPedido;
    }

    public void addItemPedido(Pedido pedido) {
        this.listaPedidos.add(pedido);

    }

    @Override
    public ArrayList<ItemPedido> busquedaPorRango(float piso, float tope) throws ItemNoEncontradoException {
        ArrayList<ItemPedido> aux = new ArrayList<>();
        String cant, nombreItemMenu, precio;

        for (Pedido ped : listaPedidos) {
            for (ItemPedido item : ped.getItemsPedido()) {
                if ((item.getItemMenu().getPrecio() >= piso) && (item.getItemMenu().getPrecio() <= tope)) {
                    aux.add(item);
                    cant = String.valueOf(item.getCantidad());
                    nombreItemMenu = item.getItemMenu().getNombre();
                    precio = String.valueOf(item.getItemMenu().getPrecio());
                    System.out.println(cant + " " + nombreItemMenu + " " + precio);
                }
            }
        }

        if (aux.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems en el rango de precios: " + piso + " a " + tope);
        }

        return aux;
    }

    @Override
    public ArrayList<ItemPedido> buscarPorVendedor(int idVendedor) throws ItemNoEncontradoException {
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
    public ArrayList<ItemPedido> filtrarCliente(int id) throws ItemNoEncontradoException {
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

        System.out.println("Items pedidos por " + cliente.getNombre() + ":");

        for (Pedido ped : listaPedidos) {
            if (cliente.equals(ped.getCliente())) {
                aux.addAll(ped.getItemsPedido());
                ped.mostrarItems();
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
    }
}
