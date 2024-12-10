/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.ClienteDAO;
import nc.dao.ItemMenuDAO;
import nc.dao.ItemPedidoDAO;
import nc.dao.memory.ClienteMemory;
import nc.dao.memory.ItemMenuMemory;
import nc.dao.memory.ItemPedidoMemory;
import nc.excepciones.CantidadItemInvalidaException;
import nc.excepciones.ItemNoEncontradoException;
import nc.excepciones.PedidoIncorrectoException;
import nc.modelo.Carrito;
import nc.modelo.Cliente;
import nc.modelo.Coordenada;
import nc.modelo.ItemMenu;
import nc.modelo.ItemPedido;

/**
 *
 * @author nicol
 */
public class ClientController {

 public void setClienteDAO(ClienteDAO clienteDAO) {
    this.clients = clienteDAO;
}

    public enum metodoPago {
        MERCADO_PAGO,
        TRANSFERENCIA
    }

    //private ItemMenuMemory items = ItemMenuMemory.getInstancia();
    //private ItemMenuMemory items = ItemMenuMemory.getInstancia();
    //private ClienteMemory clients = ClienteMemory.getInstancia();
    private ItemMenuDAO items = ItemMenuMemory.getInstancia();

    private ClienteDAO clients = ClienteMemory.getInstancia();
    private ItemPedidoDAO itemsPedido = ItemPedidoMemory.getItemPedidoMemory();
    private Cliente client;
    //private ItemPedidoMemory itemsPedido = ItemPedidoMemory.getItemPedidoMemory();
    //private ItemPedidoDAO itemsPedido = ItemPedidoMemory.getItemPedidoMemory();
    public List<List> loadData() {
        List<Cliente> data = clients.listar();
        return data.stream().map((Cliente c) -> {
            ArrayList list = new ArrayList();
            list.add(c.getId());
            list.add(c.getNombre());
            list.add(c.getDireccion());
            list.add(c.getEmail());
            return list;
        }).collect(Collectors.toList());
    }

    /*
    *Returns a list with order:
    *   Int ID
    *   String Nombre
    *   String Direccion
    *   String Email
    *   String Cuit
    *   Int Latitude
    *   Int Longitude
     */

    public void setCliente(int ID){
        Cliente cliente = clients.buscar(ID);
        this.client = cliente;
    }
    public List getCliente(int ID) {
        List clientData = new ArrayList();
        Cliente cliente = clients.buscar(ID);

        clientData.add(ID);
        clientData.add(cliente.getNombre());
        clientData.add(cliente.getDireccion());
        clientData.add(cliente.getEmail());
        clientData.add(cliente.getCuit());
        clientData.add(cliente.getCoordenada().getLatitude());
        clientData.add(cliente.getCoordenada().getLongitude());
        return clientData;
    }

    public Cliente getObjetCliente(int ID) {
        Cliente cliente = clients.buscar(ID);
        // Cliente client = ClienteMemory.getInstancia().buscar(ID);
        return cliente;
    }

    public Cliente crear(String nombre, int cuit, String email, String direccion, double latitud, double longitud) {
        Cliente cliente = new Cliente(nombre, cuit, email, direccion, latitud, longitud);
        clients.add(cliente);
        // ClienteMemory.getInstancia().add(cliente);
        return cliente;
    }

    public void modificarCliente(int ID, String nombre, String direccion, String mail, int cuit, double cx, double cy) {
        Cliente cliente = clients.buscar(ID);
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setEmail(mail);
        cliente.setCuit(cuit);
        cliente.setCoordenada(new Coordenada(cx, cy));
    }

    public void agregarProductoAlCarrito(int ID_menu, int cantidad) throws ItemNoEncontradoException {
//        ItemMenu item = items.buscar(ID_menu);
        ItemMenu item = items.getItem(ID_menu);
        if (client.getCarrito() != null) {
            client.agregarProductoAlCarrito(item, cantidad);
            System.out.println("Objeto agragado al carrito");
        } else try {
            client.crearCarrito(client, item, cantidad);
            System.out.println("Carrito creado!!");
        } catch (CantidadItemInvalidaException ex) {
            System.out.println("Cantidad incorrecta ex");
        }
    }

    public void eliminarProducto(int ID_item) throws ItemNoEncontradoException {
        ItemPedido item = itemsPedido.getItem(ID_item);
        //ItemPedido item = itemsPedido.getItemPedido(ID_item);
        client.eliminarProductoDelCarrito(item);
    }

    public List<List> getContenidoCarrito() {
        if(client==null) return null;
        List<ItemPedido> data = client.getItemsCarrito();
        if (data != null && !data.isEmpty()) {
            return data.stream().map((ItemPedido item) -> {
                ArrayList list = new ArrayList();
                list.add(item.getId());
                list.add(item.getItemMenu().getNombre());
                list.add(item.getItemMenu().getPrecio());
                list.add(item.getCantidad());
                list.add(item.getPrecio());
                return list;
            }).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public void modificarItemCarrito(int ID, int ID_item, int nuevaCantidad) {
        Carrito carrito = client.getCarrito();
        try {
            carrito.modificarCantidad(carrito.obtenerItem(ID), nuevaCantidad);
        } catch (ItemNoEncontradoException | PedidoIncorrectoException ex) {
        }
    }

    public void setMetodoPago(metodoPago metodo, List args) {
        switch (metodo) {
            case MERCADO_PAGO ->
                client.getCarrito().setMercadoPago(args.getFirst().toString());
            case TRANSFERENCIA ->
                client.getCarrito().setTransferencia(args.getFirst().toString(), (int) args.getLast());
        }
    }

    public int getVendedorCarrito() {
        Carrito carrito = client.getCarrito();
        if (carrito != null) {
            return carrito.getPedido().getVendedor().getId();
        }
        return -1;
    }
    public void cerrarCarrito(){
        Carrito carrito = client.getCarrito();
        carrito.cerrarPedido();
    }
    public String getCarritoDescription(){
        Carrito carrito = client.getCarrito();
        return carrito.toString();
    }
    public void elminar(int id){
        clients.eliminar(id);
    }
}
