package nc.diedtp;
import ItemPedidoManagement.Carrito;
import ItemPedidoManagement.ItemPedidoMemory;
import interfacesPackage.Observable;
import nc.diedtp.excepciones.CantidadItemInvalidaException;
import interfacesPackage.Observer;
import nc.diedtp.Pedido.EstadoPedido;
import java.util.Scanner;

public class Cliente implements Observer{
    private static int next_id = 0;
    private int id;
    private int cuit;
    private String email;
    private String direccion;
    private Coordenada coordenadas;
    private String nombre;
    private Carrito carrito;
    private String alias;
    private String cbu;
  
    public Cliente(){} //constructor generico para poder instanciar un cliente sin parametros
    public Cliente(String nombre, int cuit, String email, String direccion, double latitud, double longitud ){
        this.id = next_id;
        ++next_id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = new Coordenada(latitud, longitud);
        this.nombre = nombre;
    }
    
    public Coordenada getCoordenada(){
        return this.coordenadas;
    }
    public String getDireccion(){
        return direccion;
    }
    public int getId()
   {
       return id;
   }
   public int getCuit()
   {
       return cuit;
   }
     public String getEmail(){
        return email;
    }
    public String getNombre(){
        return nombre;
    }
    public Carrito getCarrito(){
        return this.carrito;
    }
     public Carrito crearCarrito(Cliente c, ItemMenu item, int cantidad, ItemPedidoMemory memory) throws CantidadItemInvalidaException {
         if(cantidad <0) throw new CantidadItemInvalidaException("La cantidad solicita es incorrecta ");
         carrito = new Carrito(memory, c, item, cantidad);
     return carrito;
    }
     
    @Override
    public String toString(){
        return "Nombre: "+nombre+"\nId: "+Integer.toString(id)+"\nCuit: "
                +Integer.toString(cuit)+"\nEmail: "+email+"\nDireccion: "+direccion;
    }
    @Override
    public void informar(Observable pedido) {
       EstadoPedido estado = this.getCarrito().getPedido().getEstado();
        System.out.println("Tu pedido ha pasado al estado " + estado);
        if(estado == EstadoPedido.EN_ENVIO){
            System.out.println("Seleccione el metodo de pago (MP / TR)");
            Scanner sc = new Scanner(System.in);
            String pago = sc.next();
            //Excepcion por metodo de pago invalido
            if(pago.equals("MP")){
                ((Pedido)pedido).setPagoMercadoPago(this.alias);
            }else if(pago.equals("TR")){
                ((Pedido)pedido).setPagoTransferencia(this.cbu, this.cuit);
            }
            ((Pedido)pedido).cerrarPedido();
            
        }
    }
    
}
