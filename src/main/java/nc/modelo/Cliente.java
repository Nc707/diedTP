package nc.modelo;
import nc.modelo.ItemPedidoManagement.Carrito;
import nc.modelo.ItemPedidoManagement.ItemPedidoMemory;
import nc.modelo.interfacesPackage.Observable;
import nc.modelo.excepciones.CantidadItemInvalidaException;
import nc.modelo.interfacesPackage.Observer;
import java.util.Date;
import java.util.Random;

public class Cliente implements Observer{
    private static int next_id = 0;
    private int id;
    private int cuit;
    private String email;
    private String direccion;
    private Coordenada coordenadas;
    private String nombre;
    private Carrito carrito;
  
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
        Pago nuevoPago = generarPago((Pedido)pedido);
        System.out.println(nuevoPago);
        /*
        EstadoPedido estado = ((Pedido)pedido).getEstado();
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
        }*/
    }
    private Pago generarPago(Pedido pedido){
        System.out.println("Enviando Pago...");
        try {
            Thread.sleep(new Random(new Date().getTime()).nextInt(500, 60000));
        } catch (InterruptedException ex) {
           
        }
        System.out.println("Pago recibido");
        return new Pago(pedido);
    }
    
}
