package nc.modelo;

import nc.modelo.excepciones.VendedorIncorrectoException;
import nc.modelo.excepciones.PedidoCerradoException;
import nc.modelo.excepciones.PedidoIncorrectoException;
import nc.modelo.metodosDePago.EstrategiaPago;
import nc.modelo.metodosDePago.PagoMercadoPago;
import nc.modelo.metodosDePago.PagoTransferencia;
import java.util.ArrayList;
import java.util.List;

import nc.modelo.interfacesPackage.Observable;
import nc.modelo.interfacesPackage.Observer;

public class Pedido implements Observable{
    private static int next_id=0;
    private int id;
    private Vendedor vendedor;
    private Cliente cliente;
    private float precio;
    private EstadoPedido estado;
    private EstrategiaPago metodoPago;
    private List<ItemPedido> items;

    public enum EstadoPedido{
        EN_CARRITO,
        RECIBIDO,
        EN_ENVIO
    }
  
        
    //cons
    public Pedido(Vendedor vendedor, Cliente cliente){
        this.vendedor = vendedor;
        this.id = next_id;
        next_id++;
        this.cliente = cliente;
        this.precio = 0.0f;
        this.estado = EstadoPedido.EN_CARRITO;
        this.items = new ArrayList<>();

    }

 void addItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException{
        if(this.estado == EstadoPedido.RECIBIDO) throw new PedidoCerradoException("El pedido esta cerrado y no se puede modificar");
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio += item.getPrecio();
        items.add(item);
    }
    public void deleteItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException, PedidoCerradoException{
        if(this.estado == EstadoPedido.RECIBIDO) throw new PedidoCerradoException("El pedido esta cerrado y no se puede modificar");
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio -= item.getPrecio();
        items.remove(item);
    }
    public void updatePrecio(float precioViejo, float precioNuevo){
        this.precio+=(precioNuevo - precioViejo);
        
    }
    public float getPrecio(){
        return this.precio;
    }
  public int getId() {
        return id;
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
    public void setPagoTransferencia(String cbu, int cuit){
        this.metodoPago = new PagoTransferencia(cbu, cuit);
    }
    public void cerrarPedido(){
        this.estado = EstadoPedido.RECIBIDO;
        precio = this.metodoPago.cerrarPago(precio);
        System.out.println("Usted ha realizado con éxito el pago del pedido por un monto de $"+ String.valueOf(precio));
    }
    @Override
    public void addSubscriptor(Observer cliente) {
        this.cliente = (Cliente) cliente;
    }

    @Override
    public void notificarSubs() {
       this.cliente.informar(this);
    }
    public void cambioEstado(EstadoPedido nuevo){
        if(nuevo == EstadoPedido.EN_ENVIO)  this.notificarSubs();
        this.estado = nuevo;
    }    
    @Override
    public String toString() {
        return "Pedido{id=" + this.id + ", cliente=" + this.cliente + "} \n Estado: " + this.estado + "\n Precio: " + this.precio + 
                "\n Metodo de pago: " + this.metodoPago + "\n Detalle: " + this.items;
    }
}
