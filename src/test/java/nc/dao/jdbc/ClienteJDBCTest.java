/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package nc.dao.jdbc;

import java.util.ArrayList;
import java.util.HashSet; 
import nc.config.DBConnector;
import nc.modelo.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class ClienteJDBCTest {
    
    public ClienteJDBCTest() {
            DBConnector.getInstance();
    }

    @Test
    public void idUnico(){
            DBConnector.getInstance();
    ClienteJDBC client = new ClienteJDBC();
    ArrayList<Cliente> clientes = client.listar();
    HashSet<Integer> ids = new HashSet<>();
    
    for(Cliente cliente : clientes){
        boolean flag = ids.add(cliente.getId());
        assertTrue(flag,"No esta duplicado" + cliente.getId());
        }
    assertEquals(clientes.size(), ids.size(), "Se reviso toda la tabla de Clientes");
    }
     @Test
   public void cuitUnico(){
    DBConnector.getInstance();
    ClienteJDBC client = new ClienteJDBC();
    ArrayList<Cliente> clientes = client.listar();
    HashSet<Long> cuit = new HashSet<>();
    
    for(Cliente cliente : clientes){
        boolean flag = cuit.add(cliente.getCuit());
        assertTrue(flag,"No esta duplicado" + cliente.getCuit());
        }
    assertEquals(clientes.size(), cuit.size(), "Se reviso toda la tabla de Clientes");
    }
   
     
    @Test
    public void idNull(){
    DBConnector.getInstance();
    ClienteJDBC client = new ClienteJDBC();
    ArrayList<Cliente> clientes = client.listar();
    HashSet<Integer> ids = new HashSet<>();
    
    for(Cliente cliente : clientes){
        assertNotNull(cliente.getId(), "Error, un ID nunca puede ser NULL");
        ids.add(cliente.getId());
        }
        assertEquals(clientes.size(), ids.size(), "Se reviso toda la tabla de Clientes");
    }
    
    
    @Test
    public void cuitNull(){
    DBConnector.getInstance();
    ClienteJDBC client = new ClienteJDBC();
    ArrayList<Cliente> clientes = client.listar();
    HashSet<Long> cuits = new HashSet<>();
    
    for(Cliente cliente : clientes){
        assertNotNull(cliente.getId(), "Error, un ID nunca puede ser NULL");
        cuits.add(cliente.getCuit());
        }
        assertEquals(clientes.size(), cuits.size(), "Se reviso toda la tabla de Clientes");
    }
}
