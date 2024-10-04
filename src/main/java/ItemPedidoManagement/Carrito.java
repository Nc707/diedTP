package ItemPedidoManagement;

import nc.diedtp.*;
import nc.diedtp.excepciones.VendedorIncorrectoException;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemPedido> itemsPed;
    private Vendedor vendedor;
    private Cliente cliente;
    private ItemPedidoMemory itemPedidoMemory;
    private Pedido pedido;

    public Carrito(ItemPedidoMemory ipm, Cliente c) {
        this.itemPedidoMemory = ipm;
        this.itemsPed = new ArrayList<>();
        this.cliente = c;
    }

    public void agregarItem(ItemMenu item, int cant) throws VendedorIncorrectoException {
        ItemPedido ip = new ItemPedido(item, cant, pedido);
        if (itemsPed.isEmpty()) {
            this.vendedor = item.getVendedor();
            this.pedido = new Pedido(this.vendedor, this.cliente);
        }
        if (!this.vendedor.equals(item.getVendedor())) {
            throw new VendedorIncorrectoException("No se pueden agregar items de distintos vendedores al mismo carrito");
        }
        itemsPed.add(ip);
    }

    public void quitarItem(ItemPedido item) {
        itemsPed.remove(item);
    }

    public void cerrarPedido() {
        
        itemPedidoMemory.addPedido(itemsPed);
        itemsPed.clear(); // Limpiar el carrito después de cerrar el pedido
    }

    //solo para debugging
    public void printItems() {
        for (ItemPedido ip : itemsPed) {
            System.out.println(ip.getItemMenu().getNombre() + " " + ip.getCantidad());
        }
    }
}
