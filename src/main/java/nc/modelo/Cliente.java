package nc.modelo;

import java.util.Date;
import java.util.List;
import java.util.Random;
import nc.excepciones.CantidadItemInvalidaException;
import nc.excepciones.PedidoIncorrectoException;
import nc.util.interfacesPackage.Observable;
import nc.util.interfacesPackage.Observer;

public class Cliente implements Observer {

    private static int next_id = 0;
    private int id;
    private long cuit;
    private String email;
    private String direccion;
    private Coordenada coordenadas;
    private String nombre;
    private Carrito carrito;

    public Cliente() {
    } //constructor generico para poder instanciar un cliente sin parametros

    //constructor para jdbc
    public Cliente(int id, String nombre, long cuit, String email, String direccion, double latitud, double longitud) {
        this.id = id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = new Coordenada(latitud, longitud);
        this.nombre = nombre;
    }

    //constructor para memory
    public Cliente(String nombre, long cuit, String email, String direccion, double latitud, double longitud) {
        this.id = next_id;
        ++next_id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = new Coordenada(latitud, longitud);
        this.nombre = nombre;
    }

    public Coordenada getCoordenada() {
        return this.coordenadas;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenadas = coordenada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /* public void setId(int id) {
        this.id = id;
    }*/
    public int getId() {
        return id;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carrito getCarrito() {
        return this.carrito;
    }

    public List<ItemPedido> getItemsCarrito() {
        if (carrito != null) {
            return this.carrito.getItems();
        } else {
            return null;
        }
    }

    public Carrito crearCarrito(Cliente c, ItemMenu item, int cantidad) throws CantidadItemInvalidaException {
        if (cantidad < 0) {
            throw new CantidadItemInvalidaException("La cantidad solicitada es incorrecta ");
        }
        if (carrito == null) {
            carrito = new Carrito(c, item, cantidad);
        }
        return carrito;
    }

    public void agregarProductoAlCarrito(ItemMenu item, int cantidad) {
        try {
            this.carrito.agregarItem(item, cantidad);  // Delegamos la operación al carrito
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void eliminarProductoDelCarrito(ItemPedido item) {
        try {
            this.carrito.quitarItem(item);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void modificarCantidad(ItemPedido item, int cantidad) throws PedidoIncorrectoException {
        this.carrito.modificarCantidad(item, cantidad);
    }

    public float verPrecio() {
        return this.carrito.verPrecio();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nId: " + Integer.toString(id) + "\nCuit: "
                + Long.toString(cuit) + "\nEmail: " + email + "\nDireccion: " + direccion;
    }

    @Override
    public void informar(Observable pedido) {
        Pago nuevoPago = generarPago((Pedido) pedido);
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

    private Pago generarPago(Pedido pedido) {
        System.out.println("Enviando Pago...");
        try {
            Thread.sleep(new Random(new Date().getTime()).nextInt(500, 60000));
        } catch (InterruptedException ex) {

        }
        System.out.println("Pago recibido");
        return new Pago(pedido);
    }

    public void setId(int id) {
        this.id = id;
    }

}
