package ItemPedidoManagement;

import nc.diedtp.*;
import nc.diedtp.excepciones.VendedorIncorrectoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.diedtp.excepciones.ItemNoEncontradoException;
import nc.diedtp.excepciones.PedidoCerradoException;
import nc.diedtp.excepciones.PedidoIncorrectoException;

public class Carrito {
    private List<ItemPedido> items;
    private ItemPedidoMemory itemPedidoMemory;
    private Pedido pedido;

    public Carrito(ItemPedidoMemory ipm, Cliente c, ItemMenu primerItemMenu, int cantidad) {
        this.itemPedidoMemory = ipm;
        this.items = new ArrayList<>();
        this.pedido = new Pedido(primerItemMenu.getVendedor(), c);
        ItemPedido primerItemPedido = new ItemPedido(primerItemMenu, cantidad, pedido);
        try {
            pedido.addItem(primerItemPedido);
        } catch (VendedorIncorrectoException | PedidoIncorrectoException | PedidoCerradoException e) {}
        items.add(primerItemPedido);
    }

    public void agregarItem(ItemMenu item, int cantidadItems) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException {
        ItemPedido itemAgregar = new ItemPedido(item, cantidadItems, pedido);
        pedido.addItem(itemAgregar);
        items.add(itemAgregar);
    }
    public void modificarCantidad(ItemPedido item, int nuevaCantidad) throws PedidoIncorrectoException{
        if(item.getPedido()!=pedido) throw new PedidoIncorrectoException("No se puede modificar en el pedido:" + pedido.toString() + " un item del pedido: " + item.getPedido().toString());
        float precioViejo = item.getPrecio();
        item.modificarCantidad(nuevaCantidad);
        pedido.updatePrecio(precioViejo, item.getPrecio());
    }
    public ItemPedido obtenerItem(int ID) throws ItemNoEncontradoException{
        ItemPedido itemAObtener = items.stream().filter(item->item.getId()==ID).collect(Collectors.toList()).getFirst();
        if(itemAObtener == null) throw new ItemNoEncontradoException("No se pudo encontrar el item con id" + ID +" en el carrito");
        return itemAObtener;
    }

    public void quitarItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException {
        pedido.deleteItem(item);
        items.remove(item);
    }

    public void cerrarPedido() {
        pedido.cerrarPedido();
        itemPedidoMemory.addPedido(items);
    }

    //solo para debugging
    public void printItems() {
        for (ItemPedido ip : items) {
            System.out.println(ip.getItemMenu().getNombre() + " " + ip.getCantidad());
        }
    }
}
