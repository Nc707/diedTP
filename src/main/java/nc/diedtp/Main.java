/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package nc.diedtp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Main {
    public static void main(String[] args) {
      ArrayList<Cliente> clientes = new ArrayList<>();
    
      
      ArrayList<Vendedor> vendedores;
      vendedores = new ArrayList<>();
      
      clientes.add(new Cliente("roberta", 34558,"robertafernandez@gmail.com","San Martin 6165", 0.0,0.0));
      clientes.add(new Cliente("pablo",58487,"pablo.perez@hotmail.com","Calchines 1562",0.0,0.0));
      clientes.add(new Cliente("segundo",45872,"segundo@gmail.com","Francia 2022",0.0,0.0));
      
      vendedores.add(new Vendedor("Jeremias", "Belgrano 962d4"));
      vendedores.add(new Vendedor("Luis", "Tucuman 8080"));
      vendedores.add(new Vendedor("Juan", "San Jeronimo 2654"));
     
      Coordenada c51 = new Coordenada(-31.6365133,-60.703816);
      Coordenada c52 = new Coordenada(-31.6171694, -60.6780453);
      System.out.println(c51);
      System.out.println(c52);
      System.out.println(c51.distance(c52));
      
      Cliente cliente = new Cliente();
        
      // Llamar al método buscarCliente
      Cliente encontrado = buscarCliente(clientes);
      
      // Verificar si se encontró el cliente
      if (encontrado != null) {
          System.out.println("Cliente encontrado: " + encontrado.getNombre());
      } else {
          System.out.println("Cliente no encontrado.");
      }
      
       // Llamar al método buscarCliente
       Cliente c1 = eliminarCliente(clientes);
      
       // Verificar si se encontró el cliente
       if (c1 != null) {
           System.out.println("Cliente eliminado: " + c1.getNombre());
       } else {
           System.out.println("Cliente no encontrado ni eliminado.");
       }
       listarClientes(clientes);
    }
    public static Cliente buscarCliente(ArrayList<Cliente> clientes) {
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea buscar");
        Scanner entrada = new Scanner(System.in);
        clie = entrada.nextLine();
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(clie)) {
                return cliente;
            }
            try {
                if (cliente.getId() == Integer.parseInt(clie)) {
                    return cliente;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepcion 
            }
        }
        entrada.close();
        return null;
    }
    public static void listarClientes(ArrayList<Cliente> clientes){
        System.out.println("Lista de Clientes:");
        for(Cliente cliente : clientes){
            System.out.println(cliente);
        }
    }
    public static Cliente eliminarCliente(ArrayList<Cliente> clientes){
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea eliminar");
        Scanner entrada = new Scanner(System.in);
        clie = entrada.nextLine();
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(clie)) {
                clientes.remove(cliente);
                return cliente;
            }
            try {
                if (cliente.getId() == Integer.parseInt(clie)) {
                    clientes.remove(cliente);
                    return cliente;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepccion. esta se puede dar por el mismo motivo q en la busqueda
            }
        }
        entrada.close();
        return null;
    }
}

