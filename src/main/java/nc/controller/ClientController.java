/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nc.dao.ClienteDAO;
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
    public enum metodoPago{
        MERCADO_PAGO,
        TRANSFERENCIA
        }

    private ItemMenuMemory items = ItemMenuMemory.getInstancia();
    private ClienteMemory clients = ClienteMemory.getInstancia();
    private ItemPedidoMemory itemsPedido = ItemPedidoMemory.getItemPedidoMemory();

    public List<List> loadData() {
        ClienteDAO database = ClienteMemory.getInstancia();
        List<Cliente> data = database.listar();
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
    public List getCliente(int ID) {
        List clientData = new ArrayList();
        Cliente client = ClienteMemory.getInstancia().buscar(ID);
        clientData.add(ID);
        clientData.add(client.getNombre());
        clientData.add(client.getDireccion());
        clientData.add(client.getEmail());
        clientData.add(client.getCuit());
        clientData.add(client.getCoordenada().getLatitude());
        clientData.add(client.getCoordenada().getLongitude());
        return clientData;
    }

    public Cliente crear(String nombre, int cuit, String email, String direccion, double latitud, double longitud) {
        Cliente cliente = new Cliente(nombre, cuit, email, direccion, latitud, longitud);
        ClienteMemory.getInstancia().add(cliente);
        return cliente;
    }

    public void modificarCliente(int ID, String nombre, String direccion, String mail, int cuit, double cx, double cy) {
        Cliente client = ClienteMemory.getInstancia().buscar(ID);
        client.setNombre(nombre);
        client.setDireccion(direccion);
        client.setEmail(mail);
        client.setCuit(cuit);
        client.setCoordenada(new Coordenada(cx, cy));
    }

    public void agregarProductoAlCarrito(int ID, int ID_menu, int cantidad){
        ItemMenu item = items.buscar(ID_menu);
        Cliente client = clients.buscar(ID);
        if(client.getCarrito()!=null)
            client.agregarProductoAlCarrito(item, cantidad);
        else try {
            client.crearCarrito(client, item, cantidad, itemsPedido);
        } catch (CantidadItemInvalidaException ex) {}
    }

    public void eliminarProducto(int ID, int ID_item) {
        ItemPedido item = itemsPedido.getItemPedido(ID_item);
        Cliente client = clients.buscar(ID);
        client.eliminarProductoDelCarrito(item);
    }
    public List<List> getContenidoCarrito(int ID){
        Cliente client = clients.buscar(ID);
        List<ItemPedido> data = client.getItemsCarrito();
        return data.stream().map((ItemPedido item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getItemMenu().getNombre());
            list.add(item.getItemMenu().getPrecio());
            list.add(item.getCantidad());
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }
    public void modificarItemCarrito(int ID, int ID_item, int nuevaCantidad){
        Cliente client = clients.buscar(ID);
        Carrito carrito = client.getCarrito();
        try {
            carrito.modificarCantidad(carrito.obtenerItem(ID), nuevaCantidad);
         } catch (ItemNoEncontradoException | PedidoIncorrectoException ex) {}
    }
    public void setMetodoPago(int ID,metodoPago metodo, List args){
        Cliente client = clients.buscar(ID);
        switch (metodo){
            case MERCADO_PAGO -> client.getCarrito().setMercadoPago(args.getFirst().toString());
            case TRANSFERENCIA -> client.getCarrito().setTransferencia(args.getFirst().toString(), (int)args.getLast());
        }
    }
    public int getVendedorCarrito(int clienteID){
        Cliente client = clients.buscar(clienteID);
        Carrito carrito = client.getCarrito();
        if(carrito!=null)return carrito.getPedido().getVendedor().getId();
        return -1;
    }
}
