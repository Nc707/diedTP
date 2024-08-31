/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package nc.diedtp;

import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class Main {
    public static void main(String[] args) {
      ArrayList<Cliente> clientes = new ArrayList<>();
    
      
      ArrayList<Vendedor> vendedores = new ArrayList<>();
      
      clientes.add(new Cliente("roberta",0,34558,"robertafernandez@gmail.com","San Martin 6165"));
      clientes.add(new Cliente("pablo",1,58487,"pablo.perez@hotmail.com","Calchines 1562"));
      clientes.add(new Cliente("segundo",2,45872,"segundo@gmail.com","Francia 2022"));
      
      vendedores.add(new Vendedor("Jeremias", "Belgrano 962d4"));
      vendedores.add(new Vendedor("Luis", "Tucuman 8080"));
      vendedores.add(new Vendedor("Juan", "San Jeronimo 2654"));
     // double dist;
      //Haversine hv = new Haversine();
     // dist = hv.haversine(-31.6365133, -60.703816, -31.6171694, -60.6780453);
      //System.out.println("distancia: " + dist);
      Cliente cliente = new Cliente();
        
      // Llamar al método buscarCliente
      Cliente encontrado = cliente.buscarCliente(clientes);
      
      // Verificar si se encontró el cliente
      if (encontrado != null) {
          System.out.println("Cliente encontrado: " + encontrado.getNombre());
      } else {
          System.out.println("Cliente no encontrado.");
      }
      
       // Llamar al método buscarCliente
       Cliente c1 = cliente.eliminarCliente(clientes);
      
       // Verificar si se encontró el cliente
       if (c1 != null) {
           System.out.println("Cliente eliminado: " + c1.getNombre());
       } else {
           System.out.println("Cliente no encontrado ni eliminado.");
       }
       cliente.listarClientes(clientes);
    }
    
}
