/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package nc.dao.jdbc;

import java.util.ArrayList;
import java.util.HashSet; 
import nc.modelo.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class ClienteJDBCTest {
    
    public ClienteJDBCTest() {
    }

    @Test
    public void idunico(){
    ClienteJDBC client = new ClienteJDBC();
    ArrayList<Cliente> clientes = client.listar();
    HashSet<Integer> ids = new HashSet<>();
    
    for(Cliente cliente : clientes){
        ids.add(cliente.getId());
        Boolean flag = ids.add(cliente.getId());
        assertTrue(flag,"No esta duplicado" + cliente.getId());
        }
    assertEquals(clientes.size(), ids.size(), "Se reviso toda la tabla de Clientes");
    }
    
}
