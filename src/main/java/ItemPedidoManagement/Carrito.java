package ItemPedidoManagement;

import nc.diedtp.*;
import nc.diedtp.excepciones.VendedorIncorrectoException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nc.diedtp.excepciones.PedidoIncorrectoException;

public class Carrito {
    private List<ItemPedido> items;
    private ItemPedidoMemory itemPedidoMemory;
    private Pedido pedido;

    public Carrito(ItemPedidoMemory ipm, Cliente c, ItemMenu primerItemMenu, int cantidad) {
        this.itemPedidoMemory = ipm;
        this.items = new ArrayList<>();
        this.pedido = new Pedido(primerItemMenu.getVendedor(), c);
        try {
            this.agregarItem(primerItemMenu, cantidad);
        } 
        catch (VendedorIncorrectoException ex) {} 
        catch (PedidoIncorrectoException ex) {}
    }

    public void agregarItem(ItemMenu item, int cantidadItems) throws VendedorIncorrectoException, PedidoIncorrectoException {
        ItemPedido itemAgregar = new ItemPedido(item, cantidadItems, pedido);
        pedido.addItem(itemAgregar);
        items.add(itemAgregar);
    }

    public void quitarItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException {
        pedido.deleteItem(item);
        items.remove(item);
    }

    public void cerrarPedido() {
        pedido.
        itemPedidoMemory.addPedido(itemsPedido);
        itemsPedido.clear(); // Limpiar el carrito después de cerrar el pedido
    }

    //solo para debugging
    public void printItems() {
        for (ItemPedido ip : itemsPedido) {
            System.out.println(ip.getItemMenu().getNombre() + " " + ip.getCantidad());
        }
    }
}
