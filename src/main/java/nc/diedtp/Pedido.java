package nc.diedtp;

import java.util.ArrayList;
import nc.diedtp.excepciones.*;
import interfacesPackage.Observable;
import interfacesPackage.Observer;

public class Pedido implements Observable{
    private static int next_id=0;
    private int id;
    private Vendedor vendedor;
    private Cliente cliente;
    private float precio;
    private EstadoPedido estado;
    private EstrategiaPago metodoPago;
    public enum EstadoPedido{
        EN_CARRITO,
        RECIBIDO,
        EN_ENVIO
    }
    public int getId() {
        return id;
    }
        
    //cons
    public Pedido(Vendedor vendedor, Cliente cliente){
        this.vendedor = vendedor;
        this.id = next_id;
        next_id++;
        this.cliente = cliente;
        this.precio = 0.0f;
        this.estado = EstadoPedido.EN_CARRITO;
    }
    public void addItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException{
        if(this.estado == EstadoPedido.RECIBIDO) throw new PedidoCerradoException("El pedido esta cerrado y no se puede modificar");
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio += item.getPrecio();
    }
    public void deleteItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException{
        if(this.estado == EstadoPedido.RECIBIDO) throw new PedidoCerradoException("El pedido esta cerrado y no se puede modificar");
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio -= item.getPrecio();
    }
    public void updatePrecio(float precioViejo, float precioNuevo){
        this.precio+=(precioNuevo - precioViejo);
        
    }
    public float getPrecio(){
        return this.precio;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    public Vendedor getVendedor(){
        return vendedor;
    }
    public EstadoPedido getEstado(){
        return estado;
    }
    public void setPagoMercadoPago(String alias){
        this.metodoPago = new PagoMercadoPago(alias);
    }
    public void setPagoTransferencia(String cbu, String cuit){
        this.metodoPago = new PagoTransferencia(cbu, cuit);
    }
    public void cerrarPedido(){
        precio = this.metodoPago.cerrarPago(precio);
        this.estado = EstadoPedido.RECIBIDO;
    }
    @Override
    public void addSubscriptor(Observer cliente) {
        this.cliente = (Cliente) cliente;
    }

    @Override
    public void notificarSubs() {
       this.cliente.informar();
    }
    void cambioEstado(EstadoPedido nuevo){
        this.notificarSubs();
        this.estado = nuevo;
    }    
}
