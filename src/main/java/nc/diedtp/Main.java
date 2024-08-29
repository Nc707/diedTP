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
      
      clientes.add(new Cliente(0,34558,"robertafernandez@gmail.com","San Martin 6165"));
      clientes.add(new Cliente(1,58487,"pablo.perez@hotmail.com","Calchines 1562"));
      clientes.add(new Cliente(2,45872,"segundo@gmail.com","Francia 2022"));
      
      vendedores.add(new Vendedor("Jeremias", "Belgrano 9624"));
      vendedores.add(new Vendedor("Luis", "Tucuman 8080"));
      vendedores.add(new Vendedor("Juan", "San Jeronimo 2654"));
      
    }
    
}
