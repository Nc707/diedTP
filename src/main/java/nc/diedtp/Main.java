 /*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package nc.diedtp;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
      ArrayList<Cliente> clientes = new ArrayList<>();
    
      
      ArrayList<Vendedor> vendedores;
      vendedores = new ArrayList<>();
      
      clientes.add(new Cliente("roberta", 34558,"robertafernandez@gmail.com","San Martin 6165", 0.0,0.0));
      clientes.add(new Cliente("pablo",58487,"pablo.perez@hotmail.com","Calchines 1562",0.0,0.0));
      clientes.add(new Cliente("segundo",45872,"segundo@gmail.com","Francia 2022",0.0,0.0));
      
      vendedores.add(new Vendedor("Jeremias", "Belgrano 9624", 4.0, 5.2));
      vendedores.add(new Vendedor("Luis", "Tucuman 8080", 2.1,-4.0));
      vendedores.add(new Vendedor("Juan", "San Jeronimo 2654",1.0,-1.12));
      Scanner entrada = new Scanner(System.in);
      listarVendedores(vendedores);
      Vendedor v_encontrado = buscarVendedor(vendedores, entrada);
      if (v_encontrado != null) {
          System.out.println("Vendedor encontrado: " + v_encontrado.getNombre());
      } else {
          System.out.println("Vendedor no encontrado.");
      }
      v_encontrado = null;
      v_encontrado = eliminarVendedor(vendedores, entrada);
      
      if (v_encontrado != null) {
           System.out.println("Vendedor eliminado: " + v_encontrado.getNombre());
       } else {
           System.out.println("Vendedor no encontrado ni eliminado.");
       }
      listarVendedores(vendedores);
      listarClientes(clientes);
      // Llamar al método buscarCliente
      Cliente encontrado = buscarCliente(clientes, entrada);
      
      // Verificar si se encontró el cliente
      if (encontrado != null) {
          System.out.println("Cliente encontrado: " + encontrado.getNombre());
      } else {
          System.out.println("Cliente no encontrado.");
      }
      
       // Llamar al método buscarCliente
       Cliente c1 = eliminarCliente(clientes, entrada);
       
       
      
       // Verificar si se encontró el cliente
       if (c1 != null) {
           System.out.println("Cliente eliminado: " + c1.getNombre());
       } else {
           System.out.println("Cliente no encontrado ni eliminado.");
       }
       entrada.close();
       listarClientes(clientes);
    }
    public static Cliente buscarCliente(ArrayList<Cliente> clientes, Scanner entrada) {
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea buscar");
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
        return null;
    }
    public static void listarClientes(ArrayList<Cliente> clientes){
        System.out.println("Lista de Clientes:");
        for(Cliente cliente : clientes){
            System.out.println(cliente);
        }
    }
    public static void listarVendedores(ArrayList<Vendedor> vendedores){
        System.out.println("Lista de Vendedores:");
        for(Vendedor vendedor : vendedores){
            System.out.println(vendedor);
        }
    }
    public static Cliente eliminarCliente(ArrayList<Cliente> clientes, Scanner entrada){
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea eliminar");
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
        return null;
    }
    public static Vendedor eliminarVendedor(ArrayList<Vendedor> vendedores, Scanner entrada){
        String clie;
        System.out.println("Ingrese el nombre o id del vendedor que desea eliminar");
        clie = entrada.nextLine();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equals(clie)) {
                vendedores.remove(vendedor);
                return vendedor;
            }
            try {
                if (vendedor.getId() == Integer.parseInt(clie)) {
                    vendedores.remove(vendedor);
                    return vendedor;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepccion. esta se puede dar por el mismo motivo q en la busqueda
            }
        }
        return null;
    }
    public static Vendedor buscarVendedor(ArrayList<Vendedor> vendedores, Scanner entrada) {
        String vend;
        System.out.println("Ingrese el nombre o id del vendedor que desea buscar");
        vend = entrada.nextLine();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equals(vend)) {
                return vendedor;
            }
            try {
                if (vendedor.getId() == Integer.parseInt(vend)) {
                    return vendedor;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepcion 
            }
        }
        return null;
    }
}

