/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo.ClienteDAOandMemory;

import java.util.ArrayList;
import nc.modelo.Cliente;

/**
 *
 * @author lucia
 */
public class ClienteMemory implements ClienteDAO {
    
    private ArrayList<Cliente> clientes = new ArrayList<>();
   private static ClienteMemory uniqueInstance;
   
    @Override
    public ArrayList<Cliente> listar() {
       return clientes;
    }
    
    public static ClienteMemory getInstancia(){
        if(uniqueInstance == null){
            uniqueInstance = new ClienteMemory();
        }
        return uniqueInstance;
    }
    
    @Override
    public void add(Cliente cliente) {
       clientes.add(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id){
                clientes.remove(i);
                return;
            }
        }
      
    }

    @Override
    public Cliente buscar(int id) {
       for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id){
                return clientes.get(i);
            }
        }
        return null;
    }

     public Cliente crear(String nombre, int cuit, String email, String direccion, double latitud, double longitud) {
        Cliente cliente = new Cliente(nombre, cuit, email, direccion, latitud, longitud);
        add(cliente);
        return cliente;
    }
}
