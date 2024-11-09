/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.modelo.Cliente;
import nc.modelo.Coordenada;
import nc.modelo.Vendedor;
import nc.dao.ClienteDAO;
import nc.dao.memory.ClienteMemory;
import nc.dao.memory.VendedorMemory;

/**
 *
 * @author nicol
 */
public class ClientController {
    public List<List> loadData(){
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
    public List getCliente(int ID){
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
    
    public void modificarCliente(int ID, String nombre, String direccion, String mail, int cuit, double cx, double cy){
        Cliente client = ClienteMemory.getInstancia().buscar(ID);
        client.setNombre(nombre);
        client.setDireccion(direccion);
        client.setEmail(mail);
        client.setCuit(cuit);
        client.setCoordenada(new Coordenada(cx, cy));
    }
}
