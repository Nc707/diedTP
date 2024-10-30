package nc.modelo.ItemPedidoManagement;

import nc.modelo.ItemPedido;
import nc.modelo.Cliente;
import nc.modelo.ItemMenu;
import nc.modelo.Pedido;
import nc.modelo.excepciones.VendedorIncorrectoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.modelo.excepciones.ItemNoEncontradoException;
import nc.modelo.excepciones.PedidoCerradoException;
import nc.modelo.excepciones.PedidoIncorrectoException;
import java.lang.StringBuffer;
import java.util.HashSet;
import java.util.Set;

public class Carrito {
    private ArrayList<ItemPedido> items;
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
    public Pedido getPedido(){
        return this.pedido;
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
    public void setMercadoPago(String alias){
        pedido.setPagoMercadoPago(alias);
    }
    public void setTransferencia(String cbu, int cuit){
        pedido.setPagoTransferencia(cbu, cuit);
    }

    public void quitarItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException {
        pedido.deleteItem(item);
        items.remove(item);
    }

    public void cerrarPedido() {
        pedido.cerrarPedido();
        itemPedidoMemory.addPedido(items);
    }
    public float verPrecio(){
        return pedido.getPrecio();
    }

    //solo para debugging
    public List<ItemPedido> getItems() {
        return (List<ItemPedido>) items.clone();
    }
    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder("");
        representation.append("Carrito de el comprador: ").append(pedido.getCliente()).append("\n");
        representation.append("Contiene items del vendedor: ").append(pedido.getVendedor()).append("\n");
        representation.append("Está en carrito ");
        for(ItemPedido item: items){
            representation.append(item.toString()).append("\n");
        }
        representation.append("Representando un subtotal de: $").append(pedido.getPrecio()).append(" .Mas el recargo de su método de pago");
        return representation.toString();
    }
}
